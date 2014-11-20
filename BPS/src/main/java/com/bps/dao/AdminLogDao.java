package com.bps.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TadminLog;
import com.bps.model.PagingData;

/**
 * Home object for domain model class TadminLog.
 * @see com.bps.dto.TadminLog
 * @author Hibernate Tools
 */
@Repository
public class AdminLogDao extends BaseDao<TadminLog> {
	
@SuppressWarnings("rawtypes")
public PagingData findPage(final String hql, final int startNo,
                     final int pageSize, final String params)
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
