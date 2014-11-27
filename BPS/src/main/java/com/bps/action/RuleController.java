package com.bps.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.bps.commons.LogManageTools;
import com.bps.commons.SystemConstants;
import com.bps.dto.TadminLog;
import com.bps.dto.TadminUser;
import com.bps.dto.TpointRule;
import com.bps.dto.TpointRuleGroup;
import com.bps.dto.TpointRuleLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.BonusRuleService;
import com.bps.service.PointsRuleGroupService;


@Controller
public class RuleController extends BaseController {
private Logger logger = Logger.getLogger(UserController.class);
	
	
	@Resource
	private BonusRuleService bonusRuleService;
	
	@Resource
	private PointsRuleGroupService pointsRuleGroupService;
	
	private String log_content;
	
	@RequestMapping(value="/rules",method=RequestMethod.GET)
	public ModelAndView rules(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		List<TpointRuleGroup> list=pointsRuleGroupService.getAllGroups();
		mav.addObject("group", list);
		request.getSession().setAttribute(Lift_Flag, "Bonus Rule");
		mav.setViewName("rules/rules");
		return mav;
	}	
	@RequestMapping(value="/rulesList",method=RequestMethod.GET)
	@ResponseBody
	public String rulesList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=bonusRuleService.loadAdminUserList(dtp);
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
	
		
	}
		
	
	/**
	 * <p>Description: 处理新增数据的ajax请求</p>
	 * @Title: addRights 
	 * @param jsonStr
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/addrules",method=RequestMethod.POST)
	@ResponseBody
	public String addRules(HttpServletRequest request,TpointRule pointRule){
        JSONObject respJson = new JSONObject();
		try{
			bonusRuleService.createAdminRole(pointRule);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/editrules",method=RequestMethod.POST)
	@ResponseBody
	public String updateRules(HttpServletRequest request,TpointRule pointRule){	
		TpointRuleLog pointRuleLog = new TpointRuleLog();
		TadminLog adminLog = new TadminLog();
		adminLog.setAdminId(((TadminUser)request.getSession().getAttribute(SystemConstants.LOGINED)).getAdminId());
		pointRuleLog.setRuleId(pointRule.getRuleId());
		pointRuleLog.setFrom((short)0);
        JSONObject respJson = new JSONObject();
		try{
			bonusRuleService.updateAdminRole(pointRule);
			log_content="success:edit rules.";
			LogManageTools.writePointRuleLog(log_content, pointRuleLog);
			LogManageTools.writeAdminLog(log_content, adminLog);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);		
	}

	@RequestMapping(value="/rules/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRules(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			bonusRuleService.deleteAdminNodesByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/activaterules/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String activateRules(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			bonusRuleService.activateRulesByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	
	@RequestMapping(value="/deactivaterules/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String deactivateRules(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			bonusRuleService.deactivateRulesByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}
