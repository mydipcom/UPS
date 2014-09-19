package com.uswop.pms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.uswop.pms.dto.TadminUser;

public class AdminUserDao {
	
	
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
		 return (TadminUser)getSession().load(TadminUser.class, userId);		 
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
