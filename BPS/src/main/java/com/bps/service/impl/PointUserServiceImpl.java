package com.bps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bps.dao.PointUserDao;
import com.bps.dao.PointsLogDao;
import com.bps.dto.TpointUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.PointUserService;

@Service
public class PointUserServiceImpl implements PointUserService {
	@Autowired
	private PointUserDao pointUserDao;
	@Autowired
	private PointsLogDao pointsLogDao;

	public TpointUser getUserInfoById(String userId) {							
		TpointUser userInfo = pointUserDao.get(userId);		
		return userInfo;
	}
	
	public void createUserInfo(TpointUser userInfo) {
		pointUserDao.create(userInfo);
	}

	public void updateUserInfo(TpointUser userInfo) {
		pointUserDao.update(userInfo);
	}
	
	public void deleteUserInfo(TpointUser userInfo) {
		pointUserDao.delete(userInfo);;
	}

	public PagingData loadPoitUsersList(DataTableParamter dtp) {
		// TODO Auto-generated method stub
		return pointUserDao.findPage(dtp.iDisplayStart, dtp.iDisplayLength);
	}

	public void updateUserStatus(String[] user_id, Boolean status) {
		// TODO Auto-generated method stub
		pointUserDao.updateUserStatus(user_id, status);
	}

	public int getPointUserAmount() {
		// TODO Auto-generated method stub
		return pointUserDao.getCount();
	}

	
			
}
