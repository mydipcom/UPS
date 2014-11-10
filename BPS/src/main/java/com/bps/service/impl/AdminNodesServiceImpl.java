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
import org.springframework.stereotype.Service;

import com.bps.commons.BPSException;
import com.bps.dao.AdminNodesDao;
import com.bps.dto.TadminNodes;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
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
		

	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminNodeById</p> 
	 * <p>Description: </p> 
	 * @param nodeId
	 * @return 
	 * @see com.bps.service.AdminNodesService#getAdminNodeById(java.lang.String) 
	 */
	public TadminNodes getAdminNodeById(int nodeId) {
		// TODO Auto-generated method stub
		return adminNodesDao.get(nodeId);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminNode</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#createAdminNode(com.bps.dto.TadminNodes) 
	 */
	public void createAdminNode(TadminNodes adminNodes) {
		try{
			adminNodesDao.create(adminNodes);
			double nodeValue=Math.pow(2, adminNodes.getNodeId()-1);
			adminNodes.setBitFlag((long)nodeValue);
			adminNodesDao.update(adminNodes);
		}
		catch(BPSException be){
			throw be;
		}
		catch(Exception e){
			throw new BPSException("error.service.adminnodes.create",e);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#updateAdminNode(com.bps.dto.TadminNodes) 
	 */
	public void updateAdminNode(TadminNodes adminNodes) {
		adminNodesDao.update(adminNodes);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminNode</p> 
	 * <p>Description: </p> 
	 * @param adminNodes 
	 * @see com.bps.service.AdminNodesService#deleteAdminNode(com.bps.dto.TadminNodes) 
	 */
	public void deleteAdminNode(TadminNodes adminNodes) {
		adminNodesDao.delete(adminNodes);		
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminNodeById</p> 
	 * <p>Description: </p> 
	 * @param id 
	 * @see com.bps.service.AdminNodesService#deleteAdminNodeById(int)
	 */
	public void deleteAdminNodeById(int id){
		adminNodesDao.delete(id);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminNodesByIds</p> 
	 * <p>Description: </p> 
	 * @param ids 
	 * @see com.bps.service.AdminNodesService#deleteAdminNodesByIds(int[])
	 */
	public void deleteAdminNodesByIds(Integer[] ids){		
		adminNodesDao.deleteAll(ids);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: loadAdminNodesList</p> 
	 * <p>Description: </p> 
	 * @param rdtp
	 * @return 
	 * @see com.bps.service.AdminNodesService#loadAdminNodesList(com.bps.model.DataTableParamter)
	 */
	public PagingData loadAdminNodesList(DataTableParamter rdtp){		
		return adminNodesDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	
}
