package com.bps.service;

import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface AdminUserService {
	
	TadminUser getAdminUserById(String userId);
	
	void createAdminUser(TadminUser adminUser);
	
	void updateAdminUser(TadminUser adminUser);
	
	void updateAdminUserPassword(TadminUser adminUser);
	
	void deleteAdminUser(TadminUser adminUser);
	
	void deleteAdminUserById(int id);
	
	void deleteAdminUserByIds(String[] ids);
	
	public PagingData loadAdminUserList(DataTableParamter rdtp);
	
	int getAdminUserAmount();
		
}
