package com.bps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bps.commons.SystemConfig;
import com.bps.dao.SettingDao;
import com.bps.dto.Tsetting;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.SystemSettingService;
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

	
	@Autowired
	private SettingDao settingDao;

	
	public Tsetting getSystemsettingById(Integer Id) {
		// TODO Auto-generated method stub
		return settingDao.get(Id);
	}

	
	public void createSystemsetting(Tsetting setting) {
		// TODO Auto-generated method stub
		settingDao.create(setting);
	}

	
	public void updateSystemsetting(Tsetting setting) {
		// TODO Auto-generated method stub
		settingDao.update(setting);
	}

	
	public void deleteSystemsetting(Tsetting setting) {
		// TODO Auto-generated method stub
		settingDao.delete(setting);
	}

	
	public void deleteSystemsetting(int id) {
		// TODO Auto-generated method stub
		settingDao.delete(id);
	}

	
	public void deleteSystemsettingByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		settingDao.deleteAll(ids);
	}

	
	public PagingData loadSystemsettingList(DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		return settingDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}


	private List<Tsetting> getAllSystemSetting() {
		// TODO Auto-generated method stub
		return settingDao.LoadAll();
	}
	
	
	public void cachedSystemSettingData() {
		// TODO Auto-generated method stub
		List <Tsetting> setingList = getAllSystemSetting();
		SystemConfig.Admin_Setting_Map.clear();
		SystemConfig.Api_Access_Key = null;
		for(Tsetting setting:setingList){
			SystemConfig.Admin_Setting_Map.put(setting.getName(),setting.getValue());
		}
		SystemConfig.Api_Access_Key=SystemConfig.Admin_Setting_Map.get("api_access_key");
	}

	

}
