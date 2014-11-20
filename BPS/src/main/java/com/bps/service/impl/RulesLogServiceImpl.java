package com.bps.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.dao.PointRuleLogDao;
import com.bps.dto.TpointRule;
import com.bps.dto.TpointRuleLog;
import com.bps.dto.TpointsLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.RulesLogService;

public class RulesLogServiceImpl implements RulesLogService {
	@Autowired
	private  PointRuleLogDao pointRulesLogdao;
	
	
	public TpointRuleLog getRuleLogById(Integer Id) {
		// TODO Auto-generated method stub
		return pointRulesLogdao.get(Id);
	}

	
	public void createRuleLog(TpointRuleLog pointRuleLog) {
		// TODO Auto-generated method stub
		pointRulesLogdao.create(pointRuleLog);
	}

	
	public void deleteRuleLog(TpointRuleLog pointRuleLog) {
		// TODO Auto-generated method stub
		pointRulesLogdao.delete(pointRuleLog);
	}

	
	public void deleteRuleLogById(int id) {
		// TODO Auto-generated method stub
		
	}

	
	public void deleteRuleLogByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		pointRulesLogdao.deleteAll(ids);
	}

	
	public PagingData loadRuleLogList(DataTableParamter rdtp) {
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
			if(jsonObj.getString("ruleId") != null && !jsonObj.getString("ruleId").isEmpty()){
				
				criterionsList.add(Restrictions.eq("ruleId", jsonObj.getInteger("ruleId")));
				
			}
			
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return pointRulesLogdao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return pointRulesLogdao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
		 
	}

	@SuppressWarnings("unchecked")
	public List<TpointRuleLog> getpointRuleLogByRuleId(Integer id) {
		
		return pointRulesLogdao.getpointRuleLogRuleId(id);
	}

	
	public PagingData loadRuleLogList(Integer id, DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		String hql="from TpointRuleLog where ruleId= ?";
	
		 return pointRulesLogdao.findPage(hql, rdtp.iDisplayStart, rdtp.iDisplayLength,id);
	}
}
