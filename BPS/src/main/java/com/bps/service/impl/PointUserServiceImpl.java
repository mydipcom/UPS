package com.bps.service.impl;

import com.bps.commons.BPSException;
import com.bps.dao.PointUserDao;
import com.bps.dao.PointsLogDao;
import com.bps.dto.TpointUser;
import com.bps.dto.TpointsLog;
import com.bps.service.PointUserService;

public class PointUserServiceImpl implements PointUserService {
	
	private PointUserDao pointUserDao;		
	private PointsLogDao pointsLogDao;

	public TpointUser getUserInfoById(String userId) {							
		TpointUser userInfo = pointUserDao.getUserInfoById(userId);		
		return userInfo;
	}
	
	public void createUserInfo(TpointUser userInfo) {
		pointUserDao.createUserInfo(userInfo);
	}

	public void updateUserInfo(TpointUser userInfo) {
		pointUserDao.updateUserInfo(userInfo);
	}
	
	public void deleteUserInfo(TpointUser userInfo) {
		pointUserDao.deleteUserInfo(userInfo);;
	}
	
	public void deductPoints(String userId,int deductedPoints) {
		TpointUser userInfo = pointUserDao.getUserInfoById(userId);
		TpointsLog pointsHistory=new TpointsLog();
		pointsHistory.setPointUser(new TpointUser(userId));		
		pointsHistory.setPoints(-deductedPoints);
		
		pointsHistory.setStatus(true);
		
		if(userInfo==null){			
			throw new BPSException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		if(curPoints>=deductedPoints){
			userInfo.setPoints(curPoints-deductedPoints);
			pointUserDao.updateUserInfo(userInfo);		
			//Add points history record
			pointsHistory.setPointsBalance(curPoints-deductedPoints);
			pointsHistory.setContent("Deducted user points by API request.");
			pointsHistory.setStatus(true);
			pointsHistory.setCreatedTime(System.currentTimeMillis());
			pointsLogDao.createPointsHistory(pointsHistory);			
		}
		else{
			pointsHistory.setPointsBalance(curPoints-deductedPoints);
			pointsHistory.setContent("Failed to deduct points, as user points balance is insufficient.");
			pointsHistory.setStatus(false);
			pointsHistory.setCreatedTime(System.currentTimeMillis());
			pointsLogDao.createPointsHistory(pointsHistory);
			throw new BPSException(2001,"Your points balance is insufficient.");
		}
		
	}
	
	public void addPoints(String userId,int addedPoints) {
		TpointUser userInfo = pointUserDao.getUserInfoById(userId);
		TpointsLog pointsHistory=new TpointsLog();
		pointsHistory.setPointUser(new TpointUser(userId));
		pointsHistory.setPoints(addedPoints);
		if(userInfo==null){
			throw new BPSException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		userInfo.setPoints(curPoints+addedPoints);
		pointUserDao.updateUserInfo(userInfo);
		
		pointsHistory.setPointsBalance(curPoints+addedPoints);
		pointsHistory.setContent("Added user points by API request.");
		pointsHistory.setStatus(true);
		pointsHistory.setCreatedTime(System.currentTimeMillis());
		pointsLogDao.createPointsHistory(pointsHistory);
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

	/**
	 * @return the pointsLogDao
	 */
	public PointsLogDao getPointsLogDao() {
		return pointsLogDao;
	}

	/**
	 * @param pointsLogDao the pointsLogDao to set
	 */
	public void setPointsLogDao(PointsLogDao pointsLogDao) {
		this.pointsLogDao = pointsLogDao;
	}
}
