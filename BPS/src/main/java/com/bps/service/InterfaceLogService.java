package com.bps.service;

import com.bps.dto.TInterfaceLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceLogService {
	
	void deleteIterfaceLog(Integer[] ids);
	
	public PagingData loadInterfaceLogList(DataTableParamter rdtp);
	
	public void createInterfaceLog(TInterfaceLog interfaceLog);

}
