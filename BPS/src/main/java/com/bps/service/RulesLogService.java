package com.bps.service;


import java.util.List;

import com.bps.dto.TpointRuleLog;
import com.bps.dto.TpointsLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface RulesLogService {

	
	TpointRuleLog getRuleLogById(Integer Id);
	
	void createRuleLog(TpointRuleLog adminUser);
	
	void deleteRuleLog(TpointRuleLog adminUser);
	
	void deleteRuleLogById(int id);
	
	void deleteRuleLogByIds(Integer[] ids);
	
	public PagingData loadRuleLogList(DataTableParamter rdtp);
	
	public PagingData loadRuleLogList(Integer id,DataTableParamter rdtp);
	

}
