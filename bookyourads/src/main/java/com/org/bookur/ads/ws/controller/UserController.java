package com.org.bookur.ads.ws.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.bookur.ads.ws.constants.RequestURL;
import com.org.bookur.ads.ws.data.entity.User;
import com.org.bookur.ads.ws.exception.AdException;
import com.org.bookur.ads.ws.models.request.UserReq;
import com.org.bookur.ads.ws.models.response.AdResponse;
import com.org.bookur.ads.ws.service.AdService;
/**
 * 
 * @author Anand
 *
 */
@RestController
@RequestMapping(RequestURL.USER)
public class UserController {
	
	@Autowired
	private AdService adService;
	

	@RequestMapping(method = RequestMethod.GET, value = RequestURL.USER_LOGIN)
	public ResponseEntity<AdResponse> login(final HttpServletRequest request,final HttpServletResponse response,@RequestParam ("name")String name,@RequestParam ("password") String password) throws AdException {		
			User user =adService.getUserDetails(new UserReq(name,password));
			AdResponse adResponse=new AdResponse(user, true);
			return new ResponseEntity<AdResponse>(adResponse, HttpStatus.OK);
			
			
	}
	
	
	
}
