package com.bps.service;

import java.util.List;

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
	
	void activateUsersByIds(String[] ids);
	
	void deactivateUsersByIds(String[] ids);
	
	public PagingData loadAdminUserList(DataTableParamter rdtp);
	
	public int getAdminUserAmount();
	
	public TadminUser getTadminUsersByEmail(String email);
		
}
