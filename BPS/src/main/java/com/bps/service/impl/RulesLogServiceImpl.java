package com.bps.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
		// TODO Auto-generated method stub
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
