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
import com.bps.dto.TadminLog;
import com.bps.dto.TpointRuleLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminuserLogService;


@Controller
public class ManagerLogController extends BaseController {

	private Logger logger = Logger.getLogger(RightsController.class);
		
	@Resource
	private AdminuserLogService adminuserLogService;
		
	@RequestMapping(value="/managerlog",method=RequestMethod.GET)
	public ModelAndView adminuserslog(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
			
//		mav.addObject("user", tUser);
		mav.setViewName("managerlog/managerslog");
		return mav;
	}	
	@RequestMapping(value="/managerslogList",method=RequestMethod.GET)
	@ResponseBody
	public String AdminuserlogsList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=adminuserLogService.loadAdminLogList(dtp);
		pagingData.setSEcho(dtp.sEcho);		
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	@RequestMapping(value="/managerslog/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteAdminuserslog(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			adminuserLogService.deleteRuleLogById(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/managerslogview/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String viewAdminuserLogs(@PathVariable String ids,HttpServletRequest request){
		//String[] idstrArr=ids.split(",");		
		//Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			List<TadminLog> adminslog=adminuserLogService.getpointRuleLogByadminId(ids);
			respJson.put("status", true);
			respJson.put("adminslog", adminslog);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}


