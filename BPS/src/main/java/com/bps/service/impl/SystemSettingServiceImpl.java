package com.bps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bps.dao.SettingDao;
import com.bps.dto.Tsetting;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.SystemSettingService;

public class SystemSettingServiceImpl implements SystemSettingService {

	
	@Autowired
	private SettingDao settingDao;

	@Override
	public Tsetting getSystemsettingById(Integer Id) {
		// TODO Auto-generated method stub
		return settingDao.get(Id);
	}

	@Override
	public void createSystemsetting(Tsetting setting) {
		// TODO Auto-generated method stub
		settingDao.create(setting);
	}

	@Override
	public void updateSystemsetting(Tsetting setting) {
		// TODO Auto-generated method stub
		settingDao.update(setting);
	}

	@Override
	public void deleteSystemsetting(Tsetting setting) {
		// TODO Auto-generated method stub
		settingDao.delete(setting);
	}

	@Override
	public void deleteSystemsetting(int id) {
		// TODO Auto-generated method stub
		settingDao.delete(id);
	}

	@Override
	public void deleteSystemsettingByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		settingDao.deleteAll(ids);
	}

	@Override
	public PagingData loadSystemsettingList(DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		return settingDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
	

	

}
