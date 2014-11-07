package com.bps.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bps.dao.base.BaseDao;
import com.bps.dto.TadminNodes;

/**
 * 
 * <p>访问AdminNodes的DAO对象</p>
 * @ClassName: AdminNodesDao 
 * @author Phills Li 
 *
 */
@Repository
public class AdminNodesDao extends BaseDao<TadminNodes> {
	
	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {   
      super.setSessionFactory(sessionFactory);   
    } 
			
}
