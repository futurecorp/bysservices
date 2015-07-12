package com.org.bookur.ads.ws.filters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 /**
  * This class is used to validate the script injunction.
  * @author Anand
  *
  */
public class AntiXSSRequestWrapper extends HttpServletRequestWrapper {
	 private final String DEFAULT_ENCODING =  "UTF-8"; 
	private static final Logger logger = LoggerFactory.getLogger(AntiXSSRequestWrapper.class);
 
    private static Pattern[] attcakPattern = new Pattern[]{
    	/* Script fragments*/
        Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
        Pattern.compile("\\s*<\\s*s\\s*c\\s*r\\s*i\\s*p\\s*t\\s*>(.*?)<\\s*/\\s*s\\s*c\\s*r\\s*i\\s*p\\s*t\\s*>\\s*", Pattern.CASE_INSENSITIVE),
        Pattern.compile("%3Cscript%3E(.*?)%3C%2Fscript%3E", Pattern.CASE_INSENSITIVE),
        Pattern.compile("&lt;script&gt;(.*?)&lt;/script&gt;", Pattern.CASE_INSENSITIVE),
        /* src='...'*/
        Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        /* lonely script tags*/
        Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
        Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        /* eval(...)*/
        Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        /* expression(...)*/
        Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        /* javascript:...*/
        Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
        /*vbscript:...*/
        Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
        /* DOM events handing */
        Pattern.compile("(onmousedown(.*?)=|onmousemove(.*?)=|onmmouseup(.*?)=|onmouseover(.*?)=|onmouseout(.*?)=|onload(.*?)=|onunload(.*?)=|onfocus(.*?)=|onblur(.*?)=|onchange(.*?)=|onsubmit(.*?)=|ondblclick(.*?)=|onclick(.*?)=|onkeydown(.*?)=|onkeyup(.*?)=|onkeypress(.*?)=|onmouseenter(.*?)=|onmouseleave(.*?)=|onerror(.*?)=|onselect(.*?)=|onreset(.*?)=|onabort(.*?)=|ondragdrop(.*?)=|onresize(.*?)=|onactivate(.*?)=|onafterprint(.*?)=|onmoveend(.*?)=|onafterupdate(.*?)=|onbeforeactivate(.*?)=|onbeforecopy(.*?)=|onbeforecut(.*?)=|onbeforedeactivate(.*?)=|onbeforeeditfocus(.*?)=|onbeforepaste(.*?)=|onbeforeprint(.*?)=|onbeforeunload(.*?)=|onbeforeupdate(.*?)=|onmove(.*?)=|onbounce(.*?)=|oncellchange(.*?)=|oncontextmenu(.*?)=|oncontrolselect(.*?)=|oncopy(.*?)=|oncut(.*?)=|ondataavailable(.*?)=|ondatasetchanged(.*?)=|ondatasetcomplete(.*?)=|ondeactivate(.*?)=|ondrag(.*?)=|ondragend(.*?)=|ondragenter(.*?)=|onmousewheel(.*?)=|ondragleave(.*?)=|ondragover(.*?)=|ondragstart(.*?)=|ondrop(.*?)=|onerrorupdate(.*?)=|onfilterchange(.*?)=|onfinish(.*?)=|onfocusin(.*?)=|onfocusout(.*?)=|onhashchange(.*?)=|onhelp(.*?)=|oninput(.*?)=|onlosecapture(.*?)=|onmessage(.*?)=|onmouseup(.*?)=|onmovestart(.*?)=|onoffline(.*?)=|ononline(.*?)=|onpaste(.*?)=|onpropertychange(.*?)=|onreadystatechange(.*?)=|onresizeend(.*?)=|onresizestart(.*?)=|onrowenter(.*?)=|onrowexit(.*?)=|onrowsdelete(.*?)=|onrowsinserted(.*?)=|onscroll(.*?)=|onsearch(.*?)=|onselectionchange(.*?)=|onselectstart(.*?)=|onstart(.*?)=|onstop)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    static String customXSSFilter[] = null;
    
	static
	{
		String customXSSFlilterFile = "xssFilter.config";
		StringBuffer fileContent = new StringBuffer();
		InputStream is = null;
		BufferedReader br = null;
		InputStreamReader isr =  null;
		try 
		{
			is = AntiXSSRequestWrapper.class.getResourceAsStream("/"+customXSSFlilterFile);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String thisLine = null;
			while ((thisLine = br.readLine()) != null) 
			{
				fileContent.append(thisLine + "<EOL>");
			}
		} 
		catch (FileNotFoundException fne) 
		{
			logger.error( "File Not Found", fne);
		} 
		catch (IOException ioe) 
		{
			logger.error( "IO Exception", ioe);
		}
		finally{
			
				try {
					if(null != br)
						br.close();
					if(null != isr)
						isr.close();
					if(null != is)
						is.close();
				} catch (IOException e) {
					logger.error("AntiXSSRequestWrapper : IOException",e);
				}
		}
		customXSSFilter = fileContent.toString().split("<EOL>");
	}
 
    public AntiXSSRequestWrapper(HttpServletRequest request) 
    {
    	super(request);
    }
 
    @Override
    public String[] getParameterValues(String param) {
        String[] values = super.getParameterValues(param);
 
        if (values == null) {
            return null;
        }
 
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = clenseXSS(values[i]);
        }
 
        return encodedValues;
    }
 
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
 
        return clenseXSS(value);
    }
 
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return clenseXSS(value);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Map getParameterMap() {
    	Map map = super.getParameterMap();
    	Iterator iter = (map.keySet()!=null)?map.keySet().iterator():null;
    	String key = null;
    	String[] values = null;
    	if(iter!=null) {
	    	while(iter.hasNext()) {
		    	key = (String) iter.next();
		    	if(key!=null) {
			    	values = (String[])map.get(key);
			    	for(int i=0; i<values.length; i++)
			    		values[i] = clenseXSS(values[i]);
	    			}
	    		}
    		}
    	return map;
    }
    
    private String clenseXSS(String value) {
    	logger.debug("Befor clenseXSS value " + value);
    	
    	int attackFound = 0;
    	String originalValue = value;
    	
        if (value != null) {
            try {
				value =URLDecoder.decode(value, DEFAULT_ENCODING);
			} 
            catch (Exception e) {
			}
			value = value.replaceAll("\0", "");
            for (Pattern scriptPattern : attcakPattern) 
            {
               
                if(scriptPattern.matcher(value).find())
                	attackFound += 1;
                value = scriptPattern.matcher(value).replaceAll("");
            }
            if (customXSSFilter.length > 1) 
            {
            	for (String fileScriptPatterns: customXSSFilter  )
            	{
                    if(Pattern.compile(fileScriptPatterns, Pattern.CASE_INSENSITIVE).matcher(value).find())
                    	attackFound += 1;
            		value = Pattern.compile(fileScriptPatterns, Pattern.CASE_INSENSITIVE).matcher(value).replaceAll("");

            	}
            }	
        }
        
        if(attackFound > 0){
        	logger.debug("After clenseXSS  value " + value);
        	return value;
        } else{
        	logger.debug("After clenseXSS value : originalValue " + originalValue);
        	return originalValue;
        }
    }
    

   
}