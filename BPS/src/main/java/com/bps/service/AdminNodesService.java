package com.bps.service;

import com.bps.dto.TadminNodes;
import com.bps.model.PagingData;
import com.bps.model.RightsDataTableParamter;


public interface AdminNodesService {
	
	TadminNodes getAdminNodeById(String nodeId);
	
	void createAdminRole(TadminNodes adminNodes);
	
	void updateAdminRole(TadminNodes adminNodes);
	
	void deleteAdminRole(TadminNodes adminNodes);
	
	public PagingData loadAdminNodesList(RightsDataTableParamter rdtp);
		
}
