package com.bps.service;
import com.bps.dto.TpointRule;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
public interface BonusRuleService {
	
	TpointRule getBonusRuleById(String ruleId);
	
	void createAdminRole(TpointRule pointRule);
	
	void updateAdminRole(TpointRule pointRule);
	
	void deleteAdminRole(TpointRule pointRule);
	
	void deleteAdminNodesByIds(Integer[] ids);
	
	void activateRulesByIds(Integer[] ids);
	
	void deactivateRulesByIds(Integer[] ids);
	
	public PagingData loadAdminUserList(DataTableParamter rdtp);
	
	int getBonusRuleAmount();	
}
