/**
 * 
 */
package com.org.bookur.ads.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.org.bookur.ads.ws.data.dao.GenericDao;
import com.org.bookur.ads.ws.data.entity.User;
import com.org.bookur.ads.ws.exception.AdException;
import com.org.bookur.ads.ws.models.request.UserReq;

/**
 * @author 355393
 *
 */
@Repository("adService")
public class AdServiceImpl implements AdService {
	

	@Qualifier("genericDao")
	@Autowired
	private GenericDao genericDao;
	
	public GenericDao getGenericDao() {
		return genericDao;
	}
	
	@Transactional
	public User getUserDetails(UserReq input) throws AdException {
			User user=null;	
		try {
			user=genericDao.getUserDetails(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
