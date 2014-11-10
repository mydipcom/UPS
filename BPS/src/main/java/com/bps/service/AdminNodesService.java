package com.bps.service;

import com.bps.dto.TadminNodes;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface AdminNodesService {
	
	TadminNodes getAdminNodeById(int nodeId);
	
	void createAdminNode(TadminNodes adminNodes);
	
	void updateAdminNode(TadminNodes adminNodes);
	
	void deleteAdminNode(TadminNodes adminNodes);
	
	void deleteAdminNodeById(int id);
	
	void deleteAdminNodesByIds(Integer[] ids);
	
	public PagingData loadAdminNodesList(DataTableParamter rdtp);
		
}
