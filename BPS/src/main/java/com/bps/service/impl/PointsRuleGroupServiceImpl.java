package com.bps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bps.dao.PointRuleGroupDao;
import com.bps.dto.TpointRuleGroup;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.PointsRuleGroupService;
@Service
public class PointsRuleGroupServiceImpl implements PointsRuleGroupService {
	
	@Autowired
	private PointRuleGroupDao pointRuleGroupDao;
	
	public TpointRuleGroup getpointRuleGroupById(Integer id) {
		
		return pointRuleGroupDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List<TpointRuleGroup> getAllGroups() {
		
		String hql="from TpointRuleGroup";
		
		return pointRuleGroupDao.getAllGroups();
	}

	public void createPointRuleGroup(TpointRuleGroup pointRuleGroup) {
		
		pointRuleGroupDao.create(pointRuleGroup);
	}

	public void updatePointRuleGroup(TpointRuleGroup pointRuleGroup) {
		pointRuleGroupDao.update(pointRuleGroup);
		
	}

	public void deletePointRuleGroup(TpointRuleGroup pointRuleGroup) {
		pointRuleGroupDao.delete(pointRuleGroup);
		
	}

	public void deletePointRuleGroupByIds(Integer[] ids) {
		
		pointRuleGroupDao.deleteAll(ids);
	}

	public PagingData loadGroupList(DataTableParamter rdtp) {
		
		return pointRuleGroupDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}

	public void deletePointRuleGroupById(Integer ids) {
		pointRuleGroupDao.delete(ids);
		
	}

}
