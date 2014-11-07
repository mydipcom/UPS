package com.bps.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bps.dto.TpointUser;

@Repository
public class PointUserDao {
	
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
	
	
	public TpointUser getUserInfoById(String userId){
		 return (TpointUser)getSession().load(TpointUser.class, userId);		 
	}
	
	public void createUserInfo(TpointUser userInfo){		
		getSession().save(userInfo);
		getSession().flush();
	}
	
	public void updateUserInfo(TpointUser userInfo){		
		getSession().update(userInfo);	
		getSession().flush();
	}
	
	public void deleteUserInfo(TpointUser userInfo){		
		getSession().delete(userInfo);
		getSession().flush();
	}
		
	public TpointUser findUserInfoByNamedQuery(String hqlName,String userId){
		 Query query = getSession().getNamedQuery(hqlName);
		 query.setParameter(0, userId);		 
		 return (TpointUser)query.uniqueResult();	
	}
	
	public void saveUserInfoByNamedQuery(String hqlName,TpointUser userInfo){
		Query query = getSession().getNamedQuery(hqlName);
		query.setParameter(0, userInfo.getUserId());
		query.setParameter(1, userInfo.getPoints());    	
        query.executeUpdate();		
	}
	
	public void updateUserInfoByNamedQuery(String hqlName,TpointUser userInfo){
		Query query = getSession().getNamedQuery(hqlName);
		query.setParameter(0, userInfo.getPoints());
		query.setParameter(1, userInfo.getUserId());    	
        query.executeUpdate();		
	}
}
