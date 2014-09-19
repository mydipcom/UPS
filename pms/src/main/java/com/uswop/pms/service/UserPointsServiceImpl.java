package com.uswop.pms.service;

import com.uswop.pms.commons.UswopException;
import com.uswop.pms.dao.PointsHistoryDao;
import com.uswop.pms.dao.UserPointsDao;
import com.uswop.pms.dto.TpointsHistory;
import com.uswop.pms.dto.TuserPoints;

public class UserPointsServiceImpl implements UserPointsService {
	private UserPointsDao userPointsDao;		
	private PointsHistoryDao pointsHistoryDao;

	public PointsHistoryDao getPointsHistoryDao() {
		return pointsHistoryDao;
	}

	public void setPointsHistoryDao(PointsHistoryDao pointsHistoryDao) {
		this.pointsHistoryDao = pointsHistoryDao;
	}

	public UserPointsDao getUserPointsDao() {
		return userPointsDao;
	}

	public void setUserPointsDao(UserPointsDao userPointsDao) {
		this.userPointsDao = userPointsDao;
	}

	public TuserPoints getUserInfoById(String userId) {							
		TuserPoints userInfo = userPointsDao.getUserInfoById(userId);		
		return userInfo;
	}
	
	public void createUserInfo(TuserPoints userInfo) {
		userPointsDao.createUserInfo(userInfo);
	}

	public void updateUserInfo(TuserPoints userInfo) {
		userPointsDao.updateUserInfo(userInfo);
	}
	
	public void deleteUserInfo(TuserPoints userInfo) {
		userPointsDao.deleteUserInfo(userInfo);;
	}
	
	public void deductPoints(String userId,int deductedPoints) {
		TuserPoints userInfo = userPointsDao.getUserInfoById(userId);
		TpointsHistory pointsHistory=new TpointsHistory();
		pointsHistory.setUserId(userId);
		pointsHistory.setPoints(-deductedPoints);
		
		pointsHistory.setStatus(true);
		
		if(userInfo==null){			
			throw new UswopException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		if(curPoints>=deductedPoints){
			userInfo.setPoints(curPoints-deductedPoints);
			userPointsDao.updateUserInfo(userInfo);		
			//Add points history record
			pointsHistory.setPointsBalance(curPoints-deductedPoints);
			pointsHistory.setMessage("Deducted user points by API request.");
			pointsHistory.setStatus(true);
			pointsHistory.setCreatedTime(System.currentTimeMillis());
			pointsHistoryDao.createPointsHistory(pointsHistory);			
		}
		else{
			pointsHistory.setPointsBalance(curPoints-deductedPoints);
			pointsHistory.setMessage("Failed to deduct points, as user points balance is insufficient.");
			pointsHistory.setStatus(false);
			pointsHistory.setCreatedTime(System.currentTimeMillis());
			pointsHistoryDao.createPointsHistory(pointsHistory);
			throw new UswopException(2001,"Your points balance is insufficient.");
		}
		
	}
	
	public void addPoints(String userId,int addedPoints) {
		TuserPoints userInfo = userPointsDao.getUserInfoById(userId);
		TpointsHistory pointsHistory=new TpointsHistory();
		pointsHistory.setUserId(userId);
		pointsHistory.setPoints(addedPoints);
		if(userInfo==null){
			throw new UswopException(1001,"Didn't find the record by user ID from the table userinfo.");
		}
		int curPoints=userInfo.getPoints();
		userInfo.setPoints(curPoints+addedPoints);
		userPointsDao.updateUserInfo(userInfo);
		
		pointsHistory.setPointsBalance(curPoints+addedPoints);
		pointsHistory.setMessage("Added user points by API request.");
		pointsHistory.setStatus(true);
		pointsHistory.setCreatedTime(System.currentTimeMillis());
		pointsHistoryDao.createPointsHistory(pointsHistory);
	}
}
