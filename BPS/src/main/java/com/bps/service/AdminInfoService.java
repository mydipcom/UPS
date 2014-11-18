package com.bps.service;

import com.bps.dto.TadminInfo;
import com.bps.dto.TadminUser;

public interface AdminInfoService{
	TadminInfo getAdminInfoById(String adminId);
	void updateAdminInfo(TadminInfo adminInfo);
	void updateAdminInfoAvatar(TadminInfo adminInfo);
	void createAdminInfo(TadminInfo adminInfo);
}
