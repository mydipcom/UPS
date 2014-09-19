/**   
 * @Title: PointsHistoryServiceImpl.java 
 * @Package com.uswop.pms.service 
 *
 * @Description: User Points Management System
 * 
 * @date Sep 16, 2014 3:53:59 PM
 * @version V1.0   
 */ 
package com.uswop.pms.service;

import java.util.List;

import com.uswop.pms.dao.PointsHistoryDao;
import com.uswop.pms.dto.TpointsHistory;

/** 
 * <p>Types Description</p>
 * @ClassName: PointsHistoryServiceImpl 
 * @author Phills Li 
 * 
 */
public class PointsHistoryServiceImpl implements PointsHistoryService {

	private PointsHistoryDao pointsHistoryDao;
	
	public PointsHistoryDao getPointsHistoryDao() {
		return pointsHistoryDao;
	}

	public void setPointsHistoryDao(PointsHistoryDao pointsHistoryDao) {
		this.pointsHistoryDao = pointsHistoryDao;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: getPointsHistoryById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.uswop.pms.service.PointsHistoryService#getPointsHistoryById(java.lang.String) 
	 */
	@Override
	public TpointsHistory getPointsHistoryById(String id) {
		TpointsHistory pointsHistory=pointsHistoryDao.getPointsHistoryById(id);
		return pointsHistory;
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: createPointsHistory</p> 
	 * <p>Description: </p> 
	 * @param pointsHistory 
	 * @see com.uswop.pms.service.PointsHistoryService#createPointsHistory(com.uswop.pms.dto.TpointsHistory) 
	 */
	@Override
	public void createPointsHistory(TpointsHistory pointsHistory) {
		pointsHistoryDao.createPointsHistory(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updatePointsHistory</p> 
	 * <p>Description: </p> 
	 * @param adminUser 
	 * @see com.uswop.pms.service.PointsHistoryService#updatePointsHistory(com.uswop.pms.dto.TpointsHistory) 
	 */
	@Override
	public void updatePointsHistory(TpointsHistory pointsHistory) {
		pointsHistoryDao.updatePointsHistory(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deletePointsHistory</p> 
	 * <p>Description: </p> 
	 * @param pointsHistory 
	 * @see com.uswop.pms.service.PointsHistoryService#deletePointsHistory(com.uswop.pms.dto.TpointsHistory) 
	 */
	@Override
	public void deletePointsHistory(TpointsHistory pointsHistory) {
		pointsHistoryDao.deletePointsHistory(pointsHistory);

	}

	/**
	 * (non-Javadoc)
	 * <p>Title: findPointsHistoryByStatus</p> 
	 * <p>Description: </p> 
	 * @param status
	 * @return 
	 * @see com.uswop.pms.service.PointsHistoryService#findPointsHistoryByStatus(boolean) 
	 */
	@Override
	public List<TpointsHistory> findPointsHistoryByStatus(String userId,Boolean status) {
		return pointsHistoryDao.findPointsHistoryByStatus(userId,status);		
	}

}
