package com.bps.service;

import com.bps.dto.TadminRole;

public interface AdminRoleService {
	
	TadminRole getAdminRoleById(String roleId);
	
	void createAdminRole(TadminRole adminRole);
	
	void updateAdminRole(TadminRole adminRole);
	
	void deleteAdminRole(TadminRole adminRole);
	
	
		
}
