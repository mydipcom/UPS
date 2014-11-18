package com.bps.service;

import com.bps.dto.TpointUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface PointUserService {
	
	TpointUser getUserInfoById(String userId);
	
	void createUserInfo(TpointUser userInfo);
	
	void updateUserInfo(TpointUser userInfo);
	
	void deleteUserInfo(TpointUser userInfo);
	
	PagingData loadPoitUsersList(DataTableParamter dtp);
	
	void updateUserStatus(String [] user_id,Boolean status);
	
	int getPointUserAmount();
	
}
