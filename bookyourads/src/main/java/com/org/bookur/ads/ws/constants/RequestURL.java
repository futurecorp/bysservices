package com.org.bookur.ads.ws.constants;
/**
 * 
 * @author Anand
 * This Class is used to map the URL to appropriate functionality.
 */
public class RequestURL {
	/**
	 * constructor to avoid initialization
	 */
	private RequestURL() {
	}
	
	public static final String USER ="ws/user";
	public static final String USER_LOGIN ="/login";
	public static final String FORGOT_PASSWORD ="/forgotPassword";
	public static final String DEACTIVATE_ACCOUNT ="/deactivateAccount";
}
