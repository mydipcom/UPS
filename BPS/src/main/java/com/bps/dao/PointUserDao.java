package com.bps.dao;



import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bps.commons.BPSException;
import com.bps.dao.base.BaseDao;
import com.bps.dto.TpointUser;

@Repository
public class PointUserDao extends BaseDao<TpointUser> {
	
	public void updateUserStatus(String []user_id,Boolean status){
		try {
			Session session = this.currentSession();
			String updateHql="update TpointUser t set t.status=:status where t.userId=:user_id";
			for(String id:user_id){
				Query query = session.createQuery(updateHql).setString("user_id", id).setBoolean("status", status);
				query.executeUpdate();
			}
			} catch (BPSException b) {
			
		}
	}
	
}
