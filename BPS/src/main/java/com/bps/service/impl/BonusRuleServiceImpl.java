package com.bps.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;







import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.dao.PointRuleDao;
import com.bps.dto.TpointRule;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.BonusRuleService;
@Service
public class BonusRuleServiceImpl implements BonusRuleService{

	
	@Autowired
	private PointRuleDao pointRuleDao;
	
	public TpointRule getBonusRuleById(String ruleId) {
		
		// TODO Auto-generated method stub
		return pointRuleDao.get(ruleId);
	}

	
	public void createAdminRole(TpointRule pointRule) {
		// TODO Auto-generated method stub
		pointRuleDao.create(pointRule);
		
	}

	
	public void updateAdminRole(TpointRule pointRule) {
		// TODO Auto-generated method stub
		pointRuleDao.update(pointRule);
		
	}

	
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
		
		
		String searchJsonStr=rdtp.getsSearch();
		if(searchJsonStr!=null&&!searchJsonStr.isEmpty()){
			List<Criterion> criterionsList=new ArrayList<Criterion>();
			JSONObject jsonObj= (JSONObject)JSON.parse(searchJsonStr);
			Set<String> keys=jsonObj.keySet();						
			for (String key : keys) {
				String val=jsonObj.getString(key);
				if(val!=null&&!val.isEmpty()){
					if(key=="status"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getBoolean(key)));
					}
					else if(key=="ruleId"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getInteger(key)));
					}
					else if(key=="pointRuleGroup.groupId"){
						criterionsList.add(Restrictions.eq(key, jsonObj.getInteger(key)));
					}
					else{
						criterionsList.add(Restrictions.eq(key, jsonObj.get(key)));
					}
				}
			}
			Criterion[] criterions=new Criterion[criterionsList.size()];
			int i=0;
			for (Criterion criterion : criterionsList) {
				criterions[i]=criterion;	
				i++;
			}
			return pointRuleDao.findPage(criterions,rdtp.iDisplayStart, rdtp.iDisplayLength);
		}
		return pointRuleDao.findPage(rdtp.iDisplayStart, rdtp.iDisplayLength);
	}
    
	 	public int getBonusRuleAmount() {
		// TODO Auto-generated method stub
		return pointRuleDao.getCount();
	}
	
}
