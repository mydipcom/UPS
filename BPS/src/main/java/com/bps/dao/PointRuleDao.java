package com.bps.dao;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TpointRule;

/**
 * Home object for domain model class TpointRule.
 * @see com.bps.dto.TpointRule
 * @author Hibernate Tools
 */
@Repository
public class PointRuleDao extends BaseDao<TpointRule> {
	
	public void activaterules(Integer[] ids){
		for(int i=0;i<ids.length;i++){
			Query   query= currentSession().createQuery("update TpointRule set status=? where ruleId= ?");
			query.setParameter(1, ids[i]);
			query.setParameter(0, true);
			query.executeUpdate();
			//currentSession().flush();   
		}
		
	}
	public void deactivaterules(Integer[] ids){
		for(int i=0;i<ids.length;i++){
			Query   query= currentSession().createQuery("update TpointRule set status=? where ruleId= ?");
			query.setParameter(1, ids[i]);
			query.setParameter(0, false);
			query.executeUpdate();
			//currentSession().flush();   
		}
	}
}
