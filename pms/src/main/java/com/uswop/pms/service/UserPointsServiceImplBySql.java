package com.uswop.pms.service;

import com.uswop.pms.commons.UswopException;
import com.uswop.pms.dao.UserPointsDao;
import com.uswop.pms.dto.TuserPoints;

public class UserPointsServiceImplBySql implements UserPointsService {
	private UserPointsDao userPointsDao;
			
	public UserPointsDao getUserPointsDao() {
		return userPointsDao;
	}

	public void setUserPointsDao(UserPointsDao userPointsDao) {
		this.userPointsDao = userPointsDao;
	}

	public TuserPoints getUserInfoById(String userId) {							
		TuserPoints userInfo = userPointsDao.findUserInfoByNamedQuery("findUserInfo",userId);;		
		return userInfo;
	}
	
	public void createUserInfo(TuserPoints userInfo) {
		userPointsDao.saveUserInfoByNamedQuery("addUserInfo",userInfo);
	}

	public void updateUserInfo(TuserPoints userInfo) {
		userPointsDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);
	}
	
	public void deleteUserInfo(TuserPoints userInfo) {
		userPointsDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);
	}
	
	public void deductPoints(String userId,int deductedPoints) {
		TuserPoints userInfo = userPointsDao.findUserInfoByNamedQuery("findUserInfo",userId);
		if(userInfo==null){
			throw new UswopException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		if(curPoints>=deductedPoints){
			userInfo.setPoints(curPoints-deductedPoints);
			userPointsDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);
		}
		else{
			throw new UswopException(2001,"");
		}
		
	}
	
	public void addPoints(String userId,int addedPoints) {
		TuserPoints userInfo = userPointsDao.findUserInfoByNamedQuery("findUserInfo",userId);
		if(userInfo==null){
			throw new UswopException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		userInfo.setPoints(curPoints+addedPoints);
		userPointsDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);		
	}
}
