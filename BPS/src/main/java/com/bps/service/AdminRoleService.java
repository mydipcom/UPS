package com.bps.service;

import java.util.List;

import com.bps.dto.TadminRole;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface AdminRoleService {
	
	TadminRole getAdminRoleById(int roleId);
	
	List<TadminRole> getAllAdminRoles();
	
	void createAdminRole(TadminRole adminRole);
	
	void updateAdminRole(TadminRole adminRole);
	
	void deleteAdminRole(TadminRole adminRole);
	
	void deleteAdminRoleById(int id);
	
	void deleteAdminRolesByIds(Integer[] ids);
	
	PagingData loadAdminRolesList(DataTableParamter rdtp);
		
}
