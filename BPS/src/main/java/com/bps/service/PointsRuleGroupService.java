package com.bps.service;

import java.util.List;

import com.bps.dto.TpointRuleGroup;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;

public interface PointsRuleGroupService {
	TpointRuleGroup getpointRuleGroupById(Integer id);
	
	public   List<TpointRuleGroup>  getAllGroups();
	
	void createPointRuleGroup(TpointRuleGroup pointRuleGroup);
	
	void updatePointRuleGroup(TpointRuleGroup pointRuleGroup);
	
	void deletePointRuleGroup(TpointRuleGroup pointRuleGroup);
	
	void deletePointRuleGroupByIds(Integer[] ids);
	
	void deletePointRuleGroupById(Integer ids);
	
	public PagingData loadGroupList(DataTableParamter rdtp);
	
	public boolean checkgroup(TpointRuleGroup pointRuleGroup);
}
