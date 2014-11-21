package com.bps.service;

import com.bps.dto.TadminLog;
import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface MyLogService {

	TadminLog getAdminLogById(Integer Id);
	
	public PagingData loadadminlogList(DataTableParamter rdtp ,TadminUser adminuser );
}
