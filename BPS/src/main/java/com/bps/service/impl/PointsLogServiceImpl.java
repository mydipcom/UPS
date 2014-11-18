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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bps.dao.PointsLogDao;
import com.bps.dto.TpointsLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
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
	public TpointsLog getPointsHistoryById(Integer id) {
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

	@Override
	public void deletePointsHistoryByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		pointsLogDao.deleteAll(ids);
	}

	@Override
	public PagingData loadPointLogList(DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		return pointsLogDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TpointsLog> getPointsHistoryByRuleId(Integer id) {
			String hqlName="from TpointsLog where id="+id;
		return pointsLogDao.findByHqlName(hqlName);
	}
	

}
