/**
 * 
 */
package com.org.bookur.ads.ws.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.bookur.ads.ws.constants.ErrorConstants;

/**
 * @author 355393
 *
 */
public class AdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8583096777819906849L;
	private String errorCode=ErrorConstants.COMMON_ERROR;
	
	private static final Log logger = LogFactory.getLog(AdException.class);
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public AdException() {
		super();
		setErrorCode(errorCode);
	}
	/**
	 * 
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public AdException(Exception e) {
		super();
		if(e instanceof AdException ){
			errorCode=((AdException) e).getErrorCode();
			logger.error("{\"BCDException\":{\"errorCode\":\""+errorCode+"\"}}");
		}
		else if(e instanceof Exception ){
			errorCode=(ErrorConstants.COMMON_ERROR);
			logger.error("{\"Exception\":{\"errorCode\":\""+errorCode+"\"}}");
			logger.error("",e);
		}
		setErrorCode(errorCode);
		
	}

	
	
}
