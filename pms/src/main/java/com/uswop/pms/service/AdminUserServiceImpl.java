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

import com.uswop.pms.dao.AdminUserDao;
import com.uswop.pms.dto.TadminUser;

/** 
 * <p>Types Description</p>
 * @ClassName: AdminUserServiceImpl 
 * @author Phills Li 
 * 
 */
public class AdminUserServiceImpl implements AdminUserService {

	private AdminUserDao adminUserDao;
	
	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: getAdminUserById</p> 
	 * <p>Description: </p> 
	 * @param userId
	 * @return 
	 * @see com.uswop.pms.service.AdminUserService#getAdminUserById(java.lang.String) 
	 */
	@Override
	public TadminUser getAdminUserById(String userId) {
		TadminUser adminUser=adminUserDao.getAdminUserById(userId);
		return adminUser;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.uswop.pms.service.AdminUserService#createAdminUser(com.uswop.pms.dto.TadminUser) 
	 */
	@Override
	public void createAdminUser(TadminUser adminUser) {
		adminUserDao.createAdminUser(adminUser);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.uswop.pms.service.AdminUserService#updateAdminUser(com.uswop.pms.dto.TadminUser) 
	 */
	@Override
	public void updateAdminUser(TadminUser adminUser) {
		adminUserDao.updateAdminUser(adminUser);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deleteAdminUser</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.uswop.pms.service.AdminUserService#deleteAdminUser(com.uswop.pms.dto.TadminUser) 
	 */
	@Override
	public void deleteAdminUser(TadminUser adminUser) {
		adminUserDao.deleteAdminUser(adminUser);
	}

}
