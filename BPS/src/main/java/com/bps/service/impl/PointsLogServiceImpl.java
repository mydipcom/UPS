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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bps.dao.PointsLogDao;
import com.bps.dto.TpointsLog;
import com.bps.service.PointsLogService;

/** 
 * <p>Types Description</p>
 * @ClassName: PointsHistoryServiceImpl 
 * @author Phills Li 
 * 
 */
@Service
public class PointsLogServiceImpl implements PointsLogService {
	@Autowired
	private PointsLogDao pointsLogDao;	

	/**
	 * (non-Javadoc)
	 * <p>Title: getPointsHistoryById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.bps.service.PointsLogService#getPointsHistoryById(java.lang.String) 
	 */
	public TpointsLog getPointsHistoryById(String id) {
		TpointsLog pointsHistory=pointsLogDao.get(id);
		return pointsHistory;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createPointsHistory</p> 
	 * <p>Description: </p> 
	 * @param pointsHistory 
	 * @see com.bps.service.PointsLogService#createPointsHistory(com.bps.dto.TpointsLog) 
	 */
	public void createPointsHistory(TpointsLog pointsHistory) {
		pointsLogDao.create(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updatePointsHistory</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.bps.service.PointsLogService#updatePointsHistory(com.bps.dto.TpointsLog) 
	 */
	public void updatePointsHistory(TpointsLog pointsHistory) {
		pointsLogDao.update(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deletePointsHistory</p> 
	 * <p>Description: </p> 
	 * @param pointsHistory 
	 * @see com.bps.service.PointsLogService#deletePointsHistory(com.bps.dto.TpointsLog) 
	 */
	public void deletePointsHistory(TpointsLog pointsHistory) {
		pointsLogDao.delete(pointsHistory);

	}
	

}
