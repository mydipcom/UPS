package com.bps.service;

import com.bps.dto.Param;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface InterfaceParamService {

	void createInterfaceParam(Param param);
	void deleteInterfaceParam(String[] ids);
	public PagingData loadInterfaceinparam(String id,DataTableParamter rdtp);
	public PagingData loadInterfaceoutparam(String id,DataTableParamter rdtp);
}
