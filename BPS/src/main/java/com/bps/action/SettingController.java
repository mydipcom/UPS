package com.bps.action;

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
import com.bps.dto.Tsetting;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.SystemSettingService;


@Controller
public class SettingController extends BaseController {

	private Logger logger = Logger.getLogger(RightsController.class);
	
	@Resource
	private SystemSettingService systemSettingService;
		

	@RequestMapping(value="/settings",method=RequestMethod.GET)
	public ModelAndView settings(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		
//		mav.addObject("user", tUser);
		mav.setViewName("settings/systemsetting");
		return mav;
	}
	
	@RequestMapping(value="/settingsList",method=RequestMethod.GET)
	@ResponseBody
	public String SystemsettingsList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=systemSettingService.loadSystemsettingList(dtp);
		pagingData.setSEcho(dtp.sEcho);
		
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
	@RequestMapping(value="/addsetting",method=RequestMethod.POST)
	@ResponseBody
	public String addRights(HttpServletRequest request,Tsetting setting){
		
		JSONObject respJson = new JSONObject();
		try{
			systemSettingService.createSystemsetting(setting);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/editsetting",method=RequestMethod.POST)
	@ResponseBody
	public String updateRights(HttpServletRequest request,Tsetting setting){		


		JSONObject respJson = new JSONObject();
		try{
			systemSettingService.updateSystemsetting(setting);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);		
	}

	@RequestMapping(value="/setting/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRights(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			systemSettingService.deleteSystemsettingByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}
