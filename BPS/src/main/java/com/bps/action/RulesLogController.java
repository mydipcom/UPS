package com.bps.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.ConvertTools;
import com.bps.dto.TpointRuleLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.RulesLogService;

@Controller
public class RulesLogController extends BaseController {

	private Logger logger = Logger.getLogger(RightsController.class);
	
	@Resource
	private RulesLogService rulesLogService;
	
	private Integer Ruleid;
	@RequestMapping(value="/ruleslog",method=RequestMethod.GET)
	public ModelAndView ruleslogs(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		
//		mav.addObject("user", tUser);
		mav.setViewName("ruleslog/ruleslog");
		return mav;
	}	
	/*
	@RequestMapping(value="/ruleidset/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public String viewruleslogs(@PathVariable String ids,HttpServletRequest request){
		JSONObject respJson = new JSONObject();
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);
		Ruleid=idArr[0];
//		mav.addObject("user", tUser);
		respJson.put("status", true);
		return JSON.toJSONString(respJson);	
	
	}
	*/	
	@RequestMapping(value="/ruleslogList",method=RequestMethod.GET)
	@ResponseBody
	public String ruleslogList(HttpServletRequest request,DataTableParamter dtp){
		
		
		PagingData  pagingData=rulesLogService.loadRuleLogList(dtp);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		pagingData.setSEcho(dtp.sEcho);
		String rightsListJson= JSON.toJSONString(pagingData);
		
		return rightsListJson;
		
	}
	
	@RequestMapping(value="/ruleslogList/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public String ruleslogviewList(@PathVariable String ids,HttpServletRequest request,DataTableParamter dtp){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);
		PagingData  pagingData=rulesLogService.loadRuleLogList(idArr[0], dtp);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		pagingData.setSEcho(dtp.sEcho);
		String rightsListJson= JSON.toJSONString(pagingData);
		
		return rightsListJson;
		
	}
	@RequestMapping(value="/ruleslog/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRuleslog(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			rulesLogService.deleteRuleLogByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/ruleslogview/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String viewRuleLogs(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			TpointRuleLog pointrulelog=rulesLogService.getRuleLogById(idArr[0]);
			respJson.put("status", true);
			respJson.put("pointrulelog", pointrulelog);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}

	
}
