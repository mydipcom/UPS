package com.bps.service;

import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceService {

	void deleteInterface(String[] ids);
	public PagingData loadInterfaceList(DataTableParamter rdtp);
}
