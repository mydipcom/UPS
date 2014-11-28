package com.bps.service;

import com.bps.dto.TInterface;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceService {

	void deleteInterface(String[] ids);
	void createInterface(TInterface interfaces);
	void updateInterface(TInterface interfaces);
	public PagingData loadInterfaceList(DataTableParamter rdtp);
	
	public String [] getInterfaceNameList();
}
