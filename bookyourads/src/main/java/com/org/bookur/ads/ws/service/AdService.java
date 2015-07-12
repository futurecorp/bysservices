/**
 * 
 */
package com.org.bookur.ads.ws.service;
import com.org.bookur.ads.ws.data.entity.User;
import com.org.bookur.ads.ws.exception.AdException;
import com.org.bookur.ads.ws.models.request.UserReq;


/**
 * @author 355393
 *
 */
public interface AdService {
	
	public User getUserDetails(UserReq  input) throws AdException;
}
