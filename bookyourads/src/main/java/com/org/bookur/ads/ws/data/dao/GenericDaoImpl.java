package com.org.bookur.ads.ws.data.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/*import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.stereotype.Repository;

import com.org.bookur.ads.ws.data.entity.Address;
import com.org.bookur.ads.ws.data.entity.User;
import com.org.bookur.ads.ws.models.request.UserReq;

/**
 * 
 * @author Anand
 * 
 * @param <E>
 * @param <I>
 *            This is the generic DAO Implementation class which will provide
 *            implementation for generic DB operations.
 */
@Repository("genericDao")
public class GenericDaoImpl implements GenericDao{
	
	@Qualifier("sessionFactory")
	@Autowired
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public User getUserDetails(UserReq input) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		return (User) criteria.list().get(0);
	}
}