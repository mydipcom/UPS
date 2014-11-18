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
	
	@Override
	public TpointRuleLog getRuleLogById(Integer Id) {
		// TODO Auto-generated method stub
		return pointRulesLogdao.get(Id);
	}

	@Override
	public void createRuleLog(TpointRuleLog pointRuleLog) {
		// TODO Auto-generated method stub
		pointRulesLogdao.create(pointRuleLog);
	}

	@Override
	public void deleteRuleLog(TpointRuleLog pointRuleLog) {
		// TODO Auto-generated method stub
		pointRulesLogdao.delete(pointRuleLog);
	}

	@Override
	public void deleteRuleLogById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRuleLogByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		pointRulesLogdao.deleteAll(ids);
	}

	@Override
	public PagingData loadRuleLogList(DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		return pointRulesLogdao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TpointRuleLog> getpointRuleLogByRuleId(Integer id) {
		
		return pointRulesLogdao.getpointRuleLogRuleId(id);
	}

	@Override
	public PagingData loadRuleLogList(Integer id, DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		String hql="from TpointRuleLog where ruleId= ?";
	
		 return pointRulesLogdao.findPage(hql, rdtp.iDisplayStart, rdtp.iDisplayLength,id);
	}
}
