package com.uswop.pms.service;

import com.uswop.pms.dto.TadminRole;

public interface AdminRoleService {
	
	TadminRole getAdminRoleById(String roleId);
	
	void createAdminRole(TadminRole adminRole);
	
	void updateAdminRole(TadminRole adminRole);
	
	void deleteAdminRole(TadminRole adminRole);
		
}
