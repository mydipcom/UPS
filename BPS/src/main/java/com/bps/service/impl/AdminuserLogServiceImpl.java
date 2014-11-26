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
import com.bps.dao.AdminLogDao;
import com.bps.dao.InterfaceDao;
import com.bps.dto.Param;
import com.bps.dto.TInterface;
import com.bps.dto.TadminLog;

import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminuserLogService;
@Service
public class AdminuserLogServiceImpl implements AdminuserLogService {

	private static final Object[] Param = null;
	@Autowired
	private	AdminLogDao adminLogDao;
	@Autowired
	private InterfaceDao interfaceDao;
	
	public TadminLog getRuleLogById(Integer Id) {
		// TODO Auto-generated method stub
		return adminLogDao.get(Id);
	}

	
	public void createRuleLog(TadminLog adminlog) {
		// TODO Auto-generated method stub
		adminLogDao.create(adminlog);
	}

	
	public void deleteRuleLog(TadminLog adminlog) {
		// TODO Auto-generated method stub
		adminLogDao.delete(adminlog);
	}

	
	public void deleteRuleLogById(int id) {
		// TODO Auto-generated method stub
		adminLogDao.delete(id);
	}

	
	public PagingData loadAdminLogList(DataTableParamter rdtp) {
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
			if(jsonObj.getString("adminId") != null && !jsonObj.getString("adminId").isEmpty()){
				
				criterionsList.add(Restrictions.eq("adminId", jsonObj.getString("adminId")));
				
			}
			
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return adminLogDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return adminLogDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	
	public void deleteRuleLogById(Integer[] ids) {
		// TODO Auto-generated method stub
		adminLogDao.deleteAll(ids);
	}

    
    
    public PagingData loadAdminLogList(String id, DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		String hql="from TadminLog where adminId= ?";
	
		 return adminLogDao.findPage(hql, rdtp.iDisplayStart, rdtp.iDisplayLength,id);
	}

}
