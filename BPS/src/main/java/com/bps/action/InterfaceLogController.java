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
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.InterfaceLogService;

@Controller
public class InterfaceLogController extends BaseController{
	
	@Resource
	private InterfaceLogService interfaceLogService;
	
	@RequestMapping(value="/interfacelog", method=RequestMethod.GET)
	public ModelAndView interfaceLog(HttpServletRequest request){
		ModelAndView mav= new ModelAndView();
		request.getSession().setAttribute(Lift_Flag, "Log List");
		mav.setViewName("interfacelog/interfacelog");
		
		return mav;	
	}
	@RequestMapping(value="/interfaceloglist", method=RequestMethod.GET)
	@ResponseBody
	public String interfaceLogList(HttpServletRequest request,DataTableParamter dtp){
		PagingData pagingData=interfaceLogService.loadInterfaceLogList(dtp);	
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;	
	}
	@RequestMapping(value="/interfacelog/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public String DeleteInterfaceLog(HttpServletRequest request,@PathVariable String ids){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			interfaceLogService.deleteIterfaceLog(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	
}
