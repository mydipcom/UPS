package com.bps.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.ConvertTools;
import com.bps.dao.PointRuleDao;
import com.bps.dto.TpointRuleGroup;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.PointsRuleGroupService;

@Controller
public class RulesGroupController extends BaseController{
	@Autowired
	private PointsRuleGroupService pointsRuleGroupService;
	
	@Autowired
	private PointRuleDao pointRuleDao;
	
	@RequestMapping(value="/rulesgroup",method=RequestMethod.GET)
	public ModelAndView rules(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();		
		mav.setViewName("rulesgroup/rulesgroup");
		return mav;
	}
	@RequestMapping(value="/rulesgroupList",method=RequestMethod.GET)
	@ResponseBody
	public String rulesList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=pointsRuleGroupService.loadGroupList(dtp);
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;	
	}
	@RequestMapping(value="/addrulesgroup",method=RequestMethod.POST)
	@ResponseBody
	public String addRulesGroup(HttpServletRequest request,TpointRuleGroup pointRuleGroup){
		JSONObject respJson = new JSONObject();
		try{
			if (pointsRuleGroupService.checkgroup(pointRuleGroup)) {
				respJson.put("status", true);
				respJson.put("msg", true);
			}else {
			pointsRuleGroupService.createPointRuleGroup(pointRuleGroup);
			respJson.put("status", true);
			}
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/editrulesgroup",method=RequestMethod.POST)
	@ResponseBody
	public String updateRulesGroup(HttpServletRequest request,TpointRuleGroup pointRuleGroup){		

		JSONObject respJson = new JSONObject();
		try{
			if (pointsRuleGroupService.checkgroup(pointRuleGroup)) {
				respJson.put("status", true);
				respJson.put("msg", true);
			}else {
			pointsRuleGroupService.updatePointRuleGroup(pointRuleGroup);
			respJson.put("status", true);
			}
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);		
	}

	@RequestMapping(value="/rulesgroup/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRulesGroup(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);
		
		JSONObject respJson = new JSONObject();
		try{
			for (int i = 0; i < idArr.length; i++) {
				TpointRuleGroup pointRuleGroup=pointsRuleGroupService.getpointRuleGroupById(idArr[i]);
				if (!pointRuleGroup.getGroupName().equals("Base Group")) {
					pointRuleDao.changegroup(pointRuleGroup);
					pointsRuleGroupService.deletePointRuleGroupById(idArr[i]);		
				}
				else{
					respJson.put("msg", true);
				}
			}
		//	pointsRuleGroupService.deletePointRuleGroupByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}
