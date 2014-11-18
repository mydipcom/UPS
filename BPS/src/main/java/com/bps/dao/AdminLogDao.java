package com.bps.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TadminLog;

/**
 * Home object for domain model class TadminLog.
 * @see com.bps.dto.TadminLog
 * @author Hibernate Tools
 */
@Repository
public class AdminLogDao extends BaseDao<TadminLog> {
	
	
public  List<TadminLog> getadminLogRbyName(String id){
		
		Query   query= currentSession().createQuery("from TadminLog where adminId= ?");
		query.setParameter(0, id);
		List<TadminLog> list = query.list();	
		return list;
	}
	
}
