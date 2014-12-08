package com.bps.service;
import com.bps.dto.TpointRule;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
public interface BonusRuleService {
	
	TpointRule getBonusRuleById(String ruleId);
	
	void createRules(TpointRule pointRule);
	
	void updateRules(TpointRule pointRule);
	
	void deleteRules(TpointRule pointRule);
	
	void deleteRulesByIds(Integer[] ids);
	
	void activateRulesByIds(Integer[] ids);
	
	void deactivateRulesByIds(Integer[] ids);
	
	public PagingData loadRulesList(DataTableParamter rdtp);
	
	public boolean checkrules(TpointRule pointRule);
	
	int getBonusRuleAmount();	
}
