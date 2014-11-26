package com.bps.service;

import com.bps.dto.TadminNodes;
import com.bps.dto.Tsetting;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface SystemSettingService {
	
	Tsetting getSystemsettingById(Integer Id);
	
	void createSystemsetting(Tsetting setting);
	
	void updateSystemsetting(Tsetting setting);
	
	void deleteSystemsetting(Tsetting setting);
	
	void deleteSystemsetting(int id);
	
	void deleteSystemsettingByIds(Integer[] ids);
	
	public PagingData loadSystemsettingList(DataTableParamter rdtp);

	public void cachedSystemSettingData();

}
