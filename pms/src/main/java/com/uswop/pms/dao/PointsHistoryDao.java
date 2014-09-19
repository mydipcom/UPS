package com.uswop.pms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.uswop.pms.dto.TpointsHistory;
import com.uswop.pms.dto.TuserPoints;

public class PointsHistoryDao {
	
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
	
	
	public TpointsHistory getPointsHistoryById(String id){
		 return (TpointsHistory)getSession().load(TuserPoints.class, id);		 
	}
	
	public void createPointsHistory(TpointsHistory pointsHistory){		
		getSession().save(pointsHistory);
		getSession().flush();
	}
	
	public void updatePointsHistory(TpointsHistory pointsHistory){		
		getSession().update(pointsHistory);	
		getSession().flush();
	}
	
	public void deletePointsHistory(TpointsHistory pointsHistory){		
		getSession().delete(pointsHistory);
		getSession().flush();
	}
		
	@SuppressWarnings("unchecked")
	public List<TpointsHistory> findPointsHistoryByStatus(String userId,Boolean status){
		List<TpointsHistory> list=null;
		if(status==null){
			list=getSession().createCriteria(TpointsHistory.class)
					.add(Restrictions.eq("userId", userId)).list();
		}
		else{
			list=getSession().createCriteria(TpointsHistory.class)
					.add(Restrictions.eq("userId", userId))
					.add(Restrictions.eq("status", status.booleanValue())).list();
		}
		return list;	
	}
		
}
