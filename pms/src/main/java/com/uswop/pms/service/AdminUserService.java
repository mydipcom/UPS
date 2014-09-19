package com.uswop.pms.service;

import com.uswop.pms.dto.TadminUser;

public interface AdminUserService {
	
	TadminUser getAdminUserById(String userId);
	
	void createAdminUser(TadminUser adminUser);
	
	void updateAdminUser(TadminUser adminUser);
	
	void deleteAdminUser(TadminUser adminUser);
		
}
