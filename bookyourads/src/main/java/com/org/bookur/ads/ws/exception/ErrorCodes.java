/**
 * 
 */
package com.org.bookur.ads.ws.exception;

/**
 * @author 355393
 *
 */
public class ErrorCodes {
	 
    private final String code;
 
    private ErrorCodes(String code){
        this.code = code;
    }
    /**
     * This no content(000) for put/delete operations
     */
    public static final ErrorCodes NO_CONTENT = new ErrorCodes("000");
    public static final ErrorCodes SEARCH_NOT_FOUND = new ErrorCodes("001");
    public static final ErrorCodes INVALID_REQUEST = new ErrorCodes("006");
    public static final ErrorCodes INTERNAL_ISSUE = new ErrorCodes("007");
    public static final ErrorCodes CONNECTION_ISSUE = new ErrorCodes("008");
    public static final ErrorCodes COMMON_ERROR = new ErrorCodes("009");
    
   
 
}
