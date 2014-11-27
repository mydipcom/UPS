package com.bps.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.Param;
@Repository
public class InterfaceParamDao extends BaseDao<Param>{

	public void deleteparam(String[] ids){
		for(int i=0;i<ids.length;i++){
		String hql="delete Param where t.name=?";
		Query   query= currentSession().createQuery(hql);
		query.setParameter(0, ids[i]);
		query.executeUpdate();
		}
	}
}
