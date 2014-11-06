package com.bps.service;

import com.bps.dto.TadminUser;

public interface AdminUserService {
	
	TadminUser getAdminUserById(String userId);
	
	void createAdminUser(TadminUser adminUser);
	
	void updateAdminUser(TadminUser adminUser);
	
	void deleteAdminUser(TadminUser adminUser);
		
}
