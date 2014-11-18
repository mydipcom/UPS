package com.bps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;






import com.bps.dao.PointRuleDao;
import com.bps.dto.TpointRule;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.BonusRuleService;

public class BonusRuleServiceImpl implements BonusRuleService{

	
	@Autowired
	private PointRuleDao pointRuleDao;
	@Override
	public TpointRule getBonusRuleById(String ruleId) {
		
		// TODO Auto-generated method stub
		return pointRuleDao.get(ruleId);
	}

	@Override
	public void createAdminRole(TpointRule pointRule) {
		// TODO Auto-generated method stub
		pointRuleDao.create(pointRule);
		
	}

	@Override
	public void updateAdminRole(TpointRule pointRule) {
		// TODO Auto-generated method stub
		pointRuleDao.update(pointRule);
		
	}

	@Override
	public void deleteAdminRole(TpointRule pointRule) {
		// TODO Auto-generated method stub
		pointRuleDao.delete(pointRule);
	}
	public void activateRulesByIds(Integer[] ids){
		
		pointRuleDao.activaterules(ids);
	}
	public void deactivateRulesByIds(Integer[] ids){
		
		pointRuleDao.deactivaterules(ids);
	}
	public void deleteAdminNodesByIds(Integer[] ids){		
		pointRuleDao.deleteAll(ids);
	}

	public PagingData loadAdminUserList(DataTableParamter rdtp) {
		return pointRuleDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
    
	 	public int getBonusRuleAmount() {
		// TODO Auto-generated method stub
		return pointRuleDao.getCount();
	}
	
}
