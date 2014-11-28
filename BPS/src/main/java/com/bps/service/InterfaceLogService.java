package com.bps.service;

import java.util.List;

import com.bps.dto.TInterfaceLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceLogService {
	
	void deleteIterfaceLog(Integer[] ids);
	
	public PagingData loadInterfaceLogList(DataTableParamter rdtp);
	
	public void createInterfaceLog(TInterfaceLog interfaceLog);
	
	public List<TInterfaceLog> findByInterfaceName(String name);

}
