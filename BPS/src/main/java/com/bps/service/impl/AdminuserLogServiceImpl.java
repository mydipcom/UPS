package com.bps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bps.dao.AdminLogDao;
import com.bps.dto.TadminLog;

import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminuserLogService;

public class AdminuserLogServiceImpl implements AdminuserLogService {

	@Autowired
	private	AdminLogDao adminLogDao;
	
	
	public TadminLog getRuleLogById(Integer Id) {
		// TODO Auto-generated method stub
		return adminLogDao.get(Id);
	}

	
	public void createRuleLog(TadminLog adminlog) {
		// TODO Auto-generated method stub
		adminLogDao.create(adminlog);
	}

	
	public void deleteRuleLog(TadminLog adminlog) {
		// TODO Auto-generated method stub
		adminLogDao.delete(adminlog);
	}

	
	public void deleteRuleLogById(int id) {
		// TODO Auto-generated method stub
		adminLogDao.delete(id);
	}

	
	public PagingData loadAdminLogList(DataTableParamter rdtp) {
		// TODO Auto-generated method stub
		return adminLogDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	
	public void deleteRuleLogById(Integer[] ids) {
		// TODO Auto-generated method stub
		adminLogDao.deleteAll(ids);
	}

    public List<TadminLog> getpointRuleLogByadminId(String id) {
		
		return adminLogDao.getadminLogRbyName(id);
	}

}
