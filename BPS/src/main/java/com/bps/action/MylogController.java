package com.bps.action;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.MyLogService;


@Controller
public class MylogController extends BaseController {

	@Resource
	private MyLogService myLogService;
	
	@RequestMapping(value="/mylog",method=RequestMethod.GET)
	public ModelAndView mylogs(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();					
		mav.setViewName("mylog/mylog");
		return mav;
	}	
	@RequestMapping(value="/mylogList",method=RequestMethod.GET)
	@ResponseBody
	public String mylogsList(HttpServletRequest request,DataTableParamter dtp){	
		TadminUser adminuser=getSessionUser(request);
		
		PagingData pagingData=myLogService.loadadminlogList(dtp,adminuser);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		pagingData.setSEcho(dtp.sEcho);		
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	@RequestMapping(value="/mylogview/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String viewMyLogs(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			TadminLog adminslog=myLogService.getAdminLogById(idArr[0]);
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
