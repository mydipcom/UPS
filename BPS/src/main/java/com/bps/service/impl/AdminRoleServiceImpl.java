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

import com.bps.dao.AdminRoleDao;
import com.bps.dto.TadminRole;
import com.bps.service.AdminRoleService;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
public class AdminRoleServiceImpl implements AdminRoleService {

	private AdminRoleDao adminRoleDao;
	
	public AdminRoleDao getAdminRoleDao() {
		return adminRoleDao;
	}

	public void setAdminRoleDao(AdminRoleDao adminRoleDao) {
		this.adminRoleDao = adminRoleDao;
	}
	
	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminRoleById</p> 
	 * <p>Description: </p> 
	 * @param userId
	 * @return TadminRole
	 * @see com.bps.service.AdminUserService#getAdminUserById(java.lang.String) 
	 */
	@Override
	public TadminRole getAdminRoleById(String roleId) {
		TadminRole adminRole=adminRoleDao.getAdminRoleById(roleId);
		return adminRole;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminRole 
	 * @see com.bps.service.AdminUserService#createAdminUser(com.bps.dto.TadminUser) 
	 */
	@Override
	public void createAdminRole(TadminRole adminRole) {
		adminRoleDao.createAdminRole(adminRole);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminRole</p> 
	 * <p>Description: </p> 
	 * @param adminRole 
	 * @see com.bps.service.AdminUserService#updateAdminUser(com.bps.dto.TadminUser) 
	 */
	@Override
	public void updateAdminRole(TadminRole adminRole) {
		adminRoleDao.updateAdminRole(adminRole);

	}	

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.bps.service.AdminUserService#deleteAdminUser(com.bps.dto.TadminUser) 
	 */
	@Override
	public void deleteAdminRole(TadminRole adminRole) {
		adminRoleDao.deleteAdminRole(adminRole);
	}

}
