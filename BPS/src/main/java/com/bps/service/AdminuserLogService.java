package com.bps.service;

import java.util.List;

import com.bps.dto.TadminLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface AdminuserLogService {

	

	TadminLog getRuleLogById(Integer Id);
	
	void createAdminLog(TadminLog adminlog);
	
	void deleteRuleLog(TadminLog adminlog);
	
	void deleteRuleLogById(int id);
	
	void deleteRuleLogById(Integer[] ids);
	
	public PagingData loadAdminLogList(DataTableParamter rdtp);
	
	public PagingData loadAdminLogList(String id,DataTableParamter rdtp);
	
}
