package com.bps.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TpointRuleGroup;
@Repository
public class PointRuleGroupDao extends BaseDao<TpointRuleGroup>{

	public  List<TpointRuleGroup> getAllGroups(){
		String hql="from TpointRuleGroup";
		Query query = currentSession().createQuery(hql);
		List<TpointRuleGroup> list=query.list();
		return list;	
	}
	 public boolean checkgroup(TpointRuleGroup pointRuleGroup){
		 
		 Query queryGroup=currentSession().createQuery("from TpointRuleGroup where groupName=?");
		 queryGroup.setParameter(0, pointRuleGroup.getGroupName());
		 List<TpointRuleGroup> list=queryGroup.list();
		 if (list.size()!=0) {
			return true;
		}else {
			 return false;
		}
		
		 
	 }
}
