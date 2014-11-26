package com.bps.service;

import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceParamService {

	
	public PagingData loadInterfaceinparam(String id,DataTableParamter rdtp);
	public PagingData loadInterfaceoutparam(String id,DataTableParamter rdtp);
}
