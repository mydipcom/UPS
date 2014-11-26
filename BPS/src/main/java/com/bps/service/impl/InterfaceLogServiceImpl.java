package com.bps.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.dao.InterfaceLogDao;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.InterfaceLogService;

public class InterfaceLogServiceImpl implements InterfaceLogService{

	@Autowired
	private InterfaceLogDao interfaceLogDao;
	
	public void deleteIterfaceLog(Integer[] ids) {
	
		interfaceLogDao.deleteAll(ids);
	}

	@Override
	public PagingData loadInterfaceLogList(DataTableParamter rdtp) {
		
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
					criterionsList.add(Restrictions.between("accessTime", startLong, endLong));
			}
			if(jsonObj.getString("name") != null && !jsonObj.getString("name").isEmpty()){
				
				criterionsList.add(Restrictions.eq("name", jsonObj.getString("name")));
				
			}
			
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return interfaceLogDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		
		return interfaceLogDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

}
