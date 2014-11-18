package com.bps.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TpointRuleLog;
import com.bps.dto.TpointsLog;
import com.bps.model.PagingData;

/**
 * Home object for domain model class TpointRuleLog.
 * @see com.bps.dto.TpointRuleLog
 * @author Hibernate Tools
 */
@Repository
public class PointRuleLogDao extends BaseDao<TpointRuleLog> {
	

	
	public  List<TpointRuleLog> getpointRuleLogRuleId(Integer id){
		
		Query   query= currentSession().createQuery("from TpointRuleLog where ruleId= ?");
		query.setParameter(0, id);
		List<TpointRuleLog> list = query.list();	
		return list;
	}
	  @SuppressWarnings("rawtypes")
		public PagingData findPage(final String hql, final int startNo,
	                         final int pageSize, final Integer params)
	    {
	        String countHql = getCountHql(hql);
	        Query countQuery = currentSession().createQuery(countHql);
	        Query query = currentSession().createQuery(hql);  
	        countQuery.setParameter(0, params);
	        query.setParameter(0, params);
	        int totalCount = ((Long)countQuery.iterate().next()).intValue();
	        if (totalCount == 0)
	        {
	            return new PagingData();
	        }        
	        List list = query.setFirstResult(startNo).setMaxResults(pageSize).list();
	        PagingData page = new PagingData(totalCount, totalCount, list.toArray());        
	        return page;
	    }
}
