package com.org.bookur.ads.ws.data.dao;
import com.org.bookur.ads.ws.data.entity.User;
import com.org.bookur.ads.ws.models.request.UserReq;

/*import org.hibernate.criterion.Criterion;*/

public interface GenericDao {
     
	public User getUserDetails(UserReq  input) throws Exception;
	
	
}