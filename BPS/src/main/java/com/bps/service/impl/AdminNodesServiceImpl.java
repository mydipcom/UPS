/**   
 * @Title: AdminNodesServiceImpl.java 
 * @Package com.bps.service.impl 
 *
 * @Description: User Points Management System
 * 
 * @date Nov 1, 2014 12:57:59 PM
 * @version V1.0   
 */ 
package com.bps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bps.dao.AdminNodesDao;
import com.bps.dto.TadminNodes;
import com.bps.model.PagingData;
import com.bps.model.RightsDataTableParamter;
import com.bps.service.AdminNodesService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminNodesServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class AdminNodesServiceImpl implements AdminNodesService {	
    @Autowired
	private AdminNodesDao adminNodesDao;
	
	public AdminNodesDao getAdminNodesDao() {
		return adminNodesDao;
	}

	public void setAdminNodesDao(AdminNodesDao adminNodesDao) {
		this.adminNodesDao = adminNodesDao;
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminNodeById</p> 
	 * <p>Description: </p> 
	 * @param nodeId
	 * @return 
	 * @see com.bps.service.AdminNodesService#getAdminNodeById(java.lang.String) 
	 */
	
	public TadminNodes getAdminNodeById(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#createAdminRole(com.bps.dto.TadminNodes) 
	 */
	
	public void createAdminRole(TadminNodes adminNodes) {
		// TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#updateAdminRole(com.bps.dto.TadminNodes) 
	 */
	
	public void updateAdminRole(TadminNodes adminNodes) {
		// TODO Auto-generated method stub

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#deleteAdminRole(com.bps.dto.TadminNodes) 
	 */
	
	public void deleteAdminRole(TadminNodes adminNodes) {
		// TODO Auto-generated method stub

	}

	
	public PagingData loadAdminNodesList(RightsDataTableParamter rdtp){		
		return adminNodesDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
}
