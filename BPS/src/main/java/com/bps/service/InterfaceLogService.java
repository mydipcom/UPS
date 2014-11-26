package com.bps.service;

import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceLogService {
	
	void deleteIterfaceLog(Integer[] ids);
	
	public PagingData loadInterfaceLogList(DataTableParamter rdtp);

}
