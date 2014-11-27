package com.bps.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.ConvertTools;
import com.bps.dto.TadminLog;
import com.bps.dto.TpointsLog;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminuserLogService;
import com.bps.service.PointsLogService;


@Controller
public class PointLogController extends BaseController {

	private Logger logger = Logger.getLogger(RightsController.class);
		
	@Resource
	private PointsLogService pointsLogService;
		
	@RequestMapping(value="/pointlog",method=RequestMethod.GET)
	public ModelAndView pointlog(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
			
		request.getSession().setAttribute(Lift_Flag, "Log List");
		mav.setViewName("pointlog/pointlog");
		return mav;
	}	
	@RequestMapping(value="/pointlogList",method=RequestMethod.GET)
	@ResponseBody
	public String pointlogList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=pointsLogService.loadPointLogList(dtp);

		pagingData.setSEcho(dtp.sEcho);	
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	
	@RequestMapping(value="/pointslog/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deletepointslog(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			pointsLogService.deletePointsHistoryByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	
	@RequestMapping(value="/pointslogview/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String viewPointLogs(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			TpointsLog pointslog=pointsLogService.getPointsHistoryById(idArr[0]);
			respJson.put("status", true);
			respJson.put("pointslog", pointslog);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	
	@RequestMapping(value="/pointlogListById",method=RequestMethod.GET)
	@ResponseBody
	public String pointlogListById(HttpServletRequest request,DataTableParamter dtp,@RequestParam String id){		
	PagingData pagingData=pointsLogService.loadPointLogByUserId(dtp, id);
        pagingData.setSEcho(dtp.sEcho);	
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	
	
}


