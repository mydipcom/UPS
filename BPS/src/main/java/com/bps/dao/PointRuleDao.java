package com.bps.dao;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TpointRule;
import com.bps.dto.TpointRuleGroup;

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
	 public void  changegroup(TpointRuleGroup pointRuleGroup) {
		
		
		 Query queryBaseGroup=currentSession().createQuery("from TpointRuleGroup where groupName=?");
		 queryBaseGroup.setParameter(0, "Base Group");
		 List<TpointRuleGroup> list=queryBaseGroup.list();
		 TpointRuleGroup  baseGroup=list.get(0);
		 Session session=this.currentSession();
		 Query query = session.createQuery("Update TpointRule set pointRuleGroup.groupId ="+baseGroup.getGroupId()+"where group_id="+pointRuleGroup.getGroupId() );
		 query.executeUpdate();
		 

		
	}
}
