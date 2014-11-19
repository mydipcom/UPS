package com.bps.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
		String searchJsonStr = dtp.getsSearch();
		if(searchJsonStr != null && !searchJsonStr.isEmpty()){
			JSONObject searchJson = (JSONObject) JSON.parse(searchJsonStr);
			List <Criterion> criterionList = new ArrayList<Criterion>();
			if(searchJson.getString("userId") != null && !searchJson.getString("userId").isEmpty()){
				criterionList.add(Restrictions.eq("userId", searchJson.getString("userId")));
			}
			if(searchJson.getString("first_points") != null && !searchJson.getString("first_points").isEmpty() 
			    && searchJson.getString("end_points") != null && !searchJson.getString("end_points").isEmpty()){
				criterionList.add(Restrictions.between("points", searchJson.getIntValue("first_points"), searchJson.getIntValue("end_points")));
			}
			
			if(searchJson.getString("status") != null && !searchJson.getString("status").isEmpty()){
				criterionList.add(Restrictions.eq("status", searchJson.getBoolean("status")));
			}
			
			Criterion [] criterions = new Criterion[criterionList.size()];
			for(int i=0 ; i<criterionList.size();i++){
				criterions[i]=criterionList.get(i);
			}
			return pointUserDao.findPage(criterions, dtp.iDisplayStart, dtp.iDisplayLength);
		}
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
