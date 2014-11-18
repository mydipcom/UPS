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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
	 * @see com.bps.service.AdminNodesService#getAdminNodeById(int) 
	 */
	public TadminNodes getAdminNodeById(int nodeId) {		
		return adminNodesDao.get(nodeId);
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminNodes</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.bps.service.AdminNodesService#getAllAdminNodes()
	 */
	public List<TadminNodes> getAllAdminNodes(){
		return adminNodesDao.LoadAll();
	}
	
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminNodes</p> 
	 * <p>Description: </p> 
	 * @return List<TadminNodes>
	 * @see com.bps.service.AdminNodesService#getAllAdminNodes()
	 */
	public List<TadminNodes> getAllEnabledAdminNodes(){
		return adminNodesDao.findBy("status", true);		
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAllAdminNodesMenu</p> 
	 * <p>Description: </p> 
	 * @return List<TadminNodes>
	 * @see com.bps.service.AdminNodesService#getAllAdminNodesMenu()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminNodes> getAllAdminNodesMenu(){
		Criteria criteria=adminNodesDao.createCriteria();
		return criteria.add(Restrictions.eq("isMenu", true))
				.add(Restrictions.eq("status", true))
				.addOrder(Order.asc("groupSort"))
				.list();						
	}		
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminNodesMenuByPid</p> 
	 * <p>Description: </p> 
	 * @return List<TadminNodes>
	 * @see com.bps.service.AdminNodesService#getAdminNodesMenuByPid()
	 */
	@SuppressWarnings("unchecked")
	public List<TadminNodes> getAdminNodesMenuByPid(Integer pid){
		Criteria criteria=adminNodesDao.createCriteria();
		return criteria.add(Restrictions.eq("isMenu", true))
				.add(Restrictions.eq("status", true))
				.add(Restrictions.eq("pid", pid))
				.addOrder(Order.asc("groupSort"))
				.list();						
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
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="isMenu"||key=="status"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getBoolean(key)));
					}					
					else{
						criterionsList.add(Restrictions.eq(key, jsonObj.get(key)));
					}
				}
			}
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return adminNodesDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return adminNodesDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	
}
