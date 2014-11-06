/**   
 * @Title: PointsHistoryServiceImpl.java 
 * @Package com.uswop.pms.service 
 *
 * @Description: User Points Management System
 * 
 * @date Sep 16, 2014 3:53:59 PM
 * @version V1.0   
 */ 
package com.bps.service.impl;

import java.util.List;

import com.bps.dao.PointsLogDao;
import com.bps.dto.TpointsLog;
import com.bps.service.PointsLogService;

/** 
 * <p>Types Description</p>
 * @ClassName: PointsHistoryServiceImpl 
 * @author Phills Li 
 * 
 */
public class PointsLogServiceImpl implements PointsLogService {

	private PointsLogDao pointsLogDao;	

	/**
	 * (non-Javadoc)
	 * <p>Title: getPointsHistoryById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.bps.service.PointsLogService#getPointsHistoryById(java.lang.String) 
	 */
	@Override
	public TpointsLog getPointsHistoryById(String id) {
		TpointsLog pointsHistory=pointsLogDao.getPointsHistoryById(id);
		return pointsHistory;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createPointsHistory</p> 
	 * <p>Description: </p> 
	 * @param pointsHistory 
	 * @see com.bps.service.PointsLogService#createPointsHistory(com.bps.dto.TpointsLog) 
	 */
	@Override
	public void createPointsHistory(TpointsLog pointsHistory) {
		pointsLogDao.createPointsHistory(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updatePointsHistory</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.bps.service.PointsLogService#updatePointsHistory(com.bps.dto.TpointsLog) 
	 */
	@Override
	public void updatePointsHistory(TpointsLog pointsHistory) {
		pointsLogDao.updatePointsHistory(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deletePointsHistory</p> 
	 * <p>Description: </p> 
	 * @param pointsHistory 
	 * @see com.bps.service.PointsLogService#deletePointsHistory(com.bps.dto.TpointsLog) 
	 */
	@Override
	public void deletePointsHistory(TpointsLog pointsHistory) {
		pointsLogDao.deletePointsHistory(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: findPointsHistoryByStatus</p> 
	 * <p>Description: </p> 
	 * @param status
	 * @return 
	 * @see com.bps.service.PointsLogService#findPointsHistoryByStatus(boolean) 
	 */
	@Override
	public List<TpointsLog> findPointsHistoryByStatus(String userId,Boolean status) {
		return pointsLogDao.findPointsHistoryByStatus(userId,status);		
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
