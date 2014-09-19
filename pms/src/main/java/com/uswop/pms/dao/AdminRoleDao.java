package com.uswop.pms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uswop.pms.dto.TadminRole;

public class AdminRoleDao {
	
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
	
	
	public TadminRole getAdminRoleById(String roleId){
		 return (TadminRole)getSession().load(TadminRole.class, roleId);		 
	}
	
	public void createAdminRole(TadminRole adminRole){		
		getSession().save(adminRole);
		getSession().flush();
	}
	
	public void updateAdminRole(TadminRole adminRole){		
		getSession().update(adminRole);	
		getSession().flush();
	}
	
	public void deleteAdminRole(TadminRole adminRole){		
		getSession().delete(adminRole);
		getSession().flush();
	}
			
}
