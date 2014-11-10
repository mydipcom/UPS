package com.bps.service;

import com.bps.dto.TpointUser;

public interface PointUserService {
	
	TpointUser getUserInfoById(String userId);
	
	void createUserInfo(TpointUser userInfo);
	
	void updateUserInfo(TpointUser userInfo);
	
	void deleteUserInfo(TpointUser userInfo);
	
}
