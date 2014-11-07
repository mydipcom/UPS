package com.bps.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bps.dto.TadminUser;

@Repository
public class AdminUserDao {
	
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
	
	
	public TadminUser getAdminUserById(String userId){
		 return (TadminUser)getSession().get(TadminUser.class, userId);		 
	}
	
	public void createAdminUser(TadminUser adminUser){		
		getSession().save(adminUser);
		getSession().flush();
	}
	
	public void updateAdminUser(TadminUser adminUser){		
		getSession().update(adminUser);	
		getSession().flush();
	}
	
	public void deleteAdminUser(TadminUser adminUser){		
		getSession().delete(adminUser);
		getSession().flush();
	}
			
}
