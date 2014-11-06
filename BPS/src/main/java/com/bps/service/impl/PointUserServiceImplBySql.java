package com.bps.service.impl;

import com.bps.commons.BPSException;
import com.bps.dao.PointUserDao;
import com.bps.dto.TpointUser;
import com.bps.service.PointUserService;

public class PointUserServiceImplBySql implements PointUserService {
	private PointUserDao pointUserDao;				

	public TpointUser getUserInfoById(String userId) {							
		TpointUser userInfo = pointUserDao.findUserInfoByNamedQuery("findUserInfo",userId);;		
		return userInfo;
	}
	
	public void createUserInfo(TpointUser userInfo) {
		pointUserDao.saveUserInfoByNamedQuery("addUserInfo",userInfo);
	}

	public void updateUserInfo(TpointUser userInfo) {
		pointUserDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);
	}
	
	public void deleteUserInfo(TpointUser userInfo) {
		pointUserDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);
	}
	
	public void deductPoints(String userId,int deductedPoints) {
		TpointUser userInfo = pointUserDao.findUserInfoByNamedQuery("findUserInfo",userId);
		if(userInfo==null){
			throw new BPSException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		if(curPoints>=deductedPoints){
			userInfo.setPoints(curPoints-deductedPoints);
			pointUserDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);
		}
		else{
			throw new BPSException(2001,"");
		}
		
	}
	
	public void addPoints(String userId,int addedPoints) {
		TpointUser userInfo = pointUserDao.findUserInfoByNamedQuery("findUserInfo",userId);
		if(userInfo==null){
			throw new BPSException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		userInfo.setPoints(curPoints+addedPoints);
		pointUserDao.updateUserInfoByNamedQuery("updateUserInfo",userInfo);		
	}

	/**
	 * @return the pointUserDao
	 */
	public PointUserDao getPointUserDao() {
		return pointUserDao;
	}

	/**
	 * @param pointUserDao the pointUserDao to set
	 */
	public void setPointUserDao(PointUserDao pointUserDao) {
		this.pointUserDao = pointUserDao;
	}
}
