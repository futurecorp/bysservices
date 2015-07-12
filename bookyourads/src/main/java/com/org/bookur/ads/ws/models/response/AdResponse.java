package com.org.bookur.ads.ws.models.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Anand
 *
 */
@JsonIgnoreProperties("response")
public class AdResponse{
	
	private String response;
	private boolean serviceIndicator;
	private Object responseObj;
	
	public AdResponse(Object responseObj,boolean indicator) {
		this.responseObj=responseObj;
		this.serviceIndicator=indicator;
	}
	
	/**
	 * @return the serviceIndicator
	 */
	public boolean isServiceIndicator() {
		return serviceIndicator;
	}

	/**
	 * @param serviceIndicator the serviceIndicator to set
	 */
	public void setServiceIndicator(boolean serviceIndicator) {
		this.serviceIndicator = serviceIndicator;
	}

	/**
	 * @param responseObj the responseObj to set
	 */
	public void setResponseObj(Object responseObj) {
		this.responseObj = responseObj;
	}

	/**
	 * @return the responseObj
	 */
	public Object getResponseObj() {
		return responseObj;
	}

	/**
	 * @param responseObj the responseObj to set
	 */
	public void setResponseObj(String node,Object responseObj) {
		this.responseObj="{\"serviceIndicator\":"+true+",\""+node+"\":"+responseObj+"}";
	}

	public AdResponse(String node, Object response) {
		this.response="{\"serviceIndicator\":"+true+",\""+node+"\":"+response+"}";
	}
	
	public AdResponse(String nodes, ArrayList<?> responseList) {
		this.response="{\"serviceIndicator\":"+true+",\""+nodes+"\":"+responseList+"}";
	}
	
	public AdResponse(String code, String errorMsg, String url, boolean infoFlag) {
		this.response="{\"serviceIndicator\":"+false+",\"error\":{\"code\":\""+code+"\",\"errorMsg\":\""+errorMsg+"\",\"url\":\""+url+"\",\"infoFlag\":"+infoFlag+"}}".toString();
		
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	
public static void main(String[] args) throws Exception{
	Object ob=new Object();
	ob="use have logged in successfully";
	AdResponse adResponse=new AdResponse("user", ob);
	AdResponse adRespons1 =new AdResponse("001", "asdsada", "sadsadsa/sadsadsad/a/d/ad/sa/sdsa", false);
	ObjectMapper mapper =new ObjectMapper();
	
	System.out.println(adRespons1.getResponse());
	System.out.println("mapper-> "+mapper.writeValueAsString(adResponse.getResponse()));
}
}
