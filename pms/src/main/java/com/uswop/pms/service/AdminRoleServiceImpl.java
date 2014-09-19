/**   
 * @Title: AdminUserServiceImpl.java 
 * @Package com.uswop.service 
 *
 * @Description: User Points Management System
 * 
 * @date Sep 11, 2014 7:21:25 PM
 * @version V1.0   
 */ 
package com.uswop.pms.service;

import com.uswop.pms.dao.AdminRoleDao;
import com.uswop.pms.dto.TadminRole;

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
	 * @see com.uswop.pms.service.AdminUserService#getAdminUserById(java.lang.String) 
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
	 * @see com.uswop.pms.service.AdminUserService#createAdminUser(com.uswop.pms.dto.TadminUser) 
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
	 * @see com.uswop.pms.service.AdminUserService#updateAdminUser(com.uswop.pms.dto.TadminUser) 
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
	 * @see com.uswop.pms.service.AdminUserService#deleteAdminUser(com.uswop.pms.dto.TadminUser) 
	 */
	@Override
	public void deleteAdminRole(TadminRole adminRole) {
		adminRoleDao.deleteAdminRole(adminRole);
	}

}
