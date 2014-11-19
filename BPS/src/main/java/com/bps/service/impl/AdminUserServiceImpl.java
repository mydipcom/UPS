/**   
 * @Title: AdminUserServiceImpl.java 
 * @Package com.uswop.service 
 *
 * @Description: User Points Management System
 * 
 * @date Sep 11, 2014 7:21:25 PM
 * @version V1.0   
 */ 
package com.bps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bps.commons.SecurityTools;
import com.bps.dao.AdminUserDao;
import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminUserService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDao adminUserDao;
				

	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminUserById</p> 
	 * <p>Description: </p> 
	 * @param userId
	 * @return 
	 * @see com.bps.service.AdminUserService#getAdminUserById(java.lang.String) 
	 */
	public TadminUser getAdminUserById(String userId) {
		return adminUserDao.get(userId);	
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.bps.service.AdminUserService#createAdminUser(com.bps.dto.TadminUser) 
	 */
	public void createAdminUser(TadminUser adminUser) {
		adminUser.setPassword(SecurityTools.SHA1(adminUser.getPassword()));
		adminUserDao.create(adminUser);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.bps.service.AdminUserService#updateAdminUser(com.bps.dto.TadminUser) 
	 */
	public void updateAdminUser(TadminUser adminUser) {
		TadminUser ad=new TadminUser();
		ad.setAdminId(adminUser.getAdminId());
		ad.setAdminRole(adminUser.getAdminRole());
		ad.setPassword(SecurityTools.SHA1(adminUser.getPassword()));
		ad.setUpdatedTime(System.currentTimeMillis());
		ad.setUpdatedBy("steve");
		ad.setEmail(adminUser.getEmail());
		ad.setStatus(adminUser.getStatus());
		adminUserDao.update(ad);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.bps.service.AdminUserService#deleteAdminUser(com.bps.dto.TadminUser) 
	 */
	public void deleteAdminUser(TadminUser adminUser) {
		adminUserDao.delete(adminUser);
	}

	public void deleteAdminUserById(int id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAdminUserByIds(String[] ids) {
		adminUserDao.deleteAll(ids);
		// TODO Auto-generated method stub
		
	}

	public PagingData loadAdminUserList(DataTableParamter rdtp) {
		return adminUserDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
	
public int getAdminUserAmount() {
		// TODO Auto-generated method stub
		return adminUserDao.getCount();
	}

@Override
public void updateAdminUserPassword(TadminUser adminUser) {
	// TODO Auto-generated method stub
	adminUserDao.update(adminUser);
}

}
