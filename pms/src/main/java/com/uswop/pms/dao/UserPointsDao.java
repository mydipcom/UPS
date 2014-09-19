package com.uswop.pms.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uswop.pms.dto.TuserPoints;

public class UserPointsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Get the current Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	public TuserPoints getUserInfoById(String userId){
		 return (TuserPoints)getSession().load(TuserPoints.class, userId);		 
	}
	
	public void createUserInfo(TuserPoints userInfo){		
		getSession().save(userInfo);
		getSession().flush();
	}
	
	public void updateUserInfo(TuserPoints userInfo){		
		getSession().update(userInfo);	
		getSession().flush();
	}
	
	public void deleteUserInfo(TuserPoints userInfo){		
		getSession().delete(userInfo);
		getSession().flush();
	}
		
	public TuserPoints findUserInfoByNamedQuery(String hqlName,String userId){
		 Query query = getSession().getNamedQuery(hqlName);
		 query.setParameter(0, userId);		 
		 return (TuserPoints)query.uniqueResult();	
	}
	
	public void saveUserInfoByNamedQuery(String hqlName,TuserPoints userInfo){
		Query query = getSession().getNamedQuery(hqlName);
		query.setParameter(0, userInfo.getUserId());
		query.setParameter(1, userInfo.getPoints());    	
        query.executeUpdate();		
	}
	
	public void updateUserInfoByNamedQuery(String hqlName,TuserPoints userInfo){
		Query query = getSession().getNamedQuery(hqlName);
		query.setParameter(0, userInfo.getPoints());
		query.setParameter(1, userInfo.getUserId());    	
        query.executeUpdate();		
	}
}
