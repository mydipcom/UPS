package com.bps.service;

import com.bps.dto.TadminRoleRights;

public interface AdminRoleRightsService {
	
	TadminRoleRights getAdminRoleRightsById(int roleId);
	
	void createAdminRoleRights(TadminRoleRights adminRoleRights);
	
	void updateAdminRoleRights(TadminRoleRights adminRoleRights);
	
	void deleteAdminRoleRights(TadminRoleRights adminRoleRights);
		
}
