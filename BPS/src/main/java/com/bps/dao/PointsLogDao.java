package com.bps.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bps.dto.TpointsLog;
import com.bps.dto.TpointUser;

@Repository
public class PointsLogDao {
	
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
	
	
	public TpointsLog getPointsHistoryById(String id){
		 return (TpointsLog)getSession().load(TpointUser.class, id);		 
	}
	
	public void createPointsHistory(TpointsLog pointsHistory){		
		getSession().save(pointsHistory);
		getSession().flush();
	}
	
	public void updatePointsHistory(TpointsLog pointsHistory){		
		getSession().update(pointsHistory);	
		getSession().flush();
	}
	
	public void deletePointsHistory(TpointsLog pointsHistory){		
		getSession().delete(pointsHistory);
		getSession().flush();
	}
		
	@SuppressWarnings("unchecked")
	public List<TpointsLog> findPointsHistoryByStatus(String userId,Boolean status){
		List<TpointsLog> list=null;
		if(status==null){
			list=getSession().createCriteria(TpointsLog.class)
					.add(Restrictions.eq("userId", userId))
					.addOrder(Order.desc("id")).list();
		}
		else{
			list=getSession().createCriteria(TpointsLog.class)
					.add(Restrictions.eq("userId", userId))
					.add(Restrictions.eq("status", status.booleanValue()))
					.addOrder(Order.desc("id")).list();
		}
		return list;	
	}
		
}
