package com.bps.service;

import com.bps.dto.TadminRole;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface AdminRoleService {
	
	TadminRole getAdminRoleById(int roleId);
	
	void createAdminRole(TadminRole adminRole);
	
	void updateAdminRole(TadminRole adminRole);
	
	void deleteAdminRole(TadminRole adminRole);
	
	void deleteAdminRoleById(int id);
	
	void deleteAdminRolesByIds(Integer[] ids);
	
	PagingData loadAdminRolesList(DataTableParamter rdtp);
		
}
