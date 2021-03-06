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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.dao.PointsLogDao;
import com.bps.dto.TadminUser;
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

	
	public void deletePointsHistoryByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		pointsLogDao.deleteAll(ids);
	}

	
	public PagingData loadPointLogList(DataTableParamter rdtp) {
		String searchJsonStr=rdtp.getsSearch();
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd-MM-yyyy");
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			if(jsonObj.getString("startTime") != null && !jsonObj.getString("startTime").isEmpty()&&
					 jsonObj.getString("endTime") != null && !jsonObj.getString("endTime").isEmpty()){
				
					Date sdate = null;
					Date edate = null;
					try {
						sdate = simpleDateFormat.parse(jsonObj.getString("startTime"));
						edate = simpleDateFormat.parse(jsonObj.getString("endTime"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Long startLong=sdate.getTime();
					Long endLong=edate.getTime();
					criterionsList.add(Restrictions.between("createdTime", startLong, endLong));
			}
			if(jsonObj.getString("pointUser.userId") != null && !jsonObj.getString("pointUser.userId").isEmpty()){
				
				criterionsList.add(Restrictions.eq("pointUser.userId", jsonObj.getString("pointUser.userId")));
				
			}
			
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return pointsLogDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return pointsLogDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	@SuppressWarnings("unchecked")
	public List<TpointsLog> getPointsHistoryByRuleId(Integer id) {
			String hqlName="from TpointsLog where id="+id;
		return pointsLogDao.findByHqlName(hqlName);
	}

	public PagingData loadPointLogByUserId(DataTableParamter rdtp, String id) {
		// TODO Auto-generated method stub
		Criterion criterion = Restrictions.eq("pointUser.userId", id);
		return pointsLogDao.findPage(criterion, rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

}
