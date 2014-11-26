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
import com.bps.dto.Param;
import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.InterfaceModel;
import com.bps.model.PagingData;
import com.bps.service.InterfaceParamService;
import com.bps.service.InterfaceService;

@Controller
public class InterfaceController extends BaseController{
	
	@Resource
	private InterfaceService interfaceService;
	
	@Resource
	private InterfaceParamService interfaceParamService;
	
	@RequestMapping(value="/interface",method=RequestMethod.GET)
	public ModelAndView interfaces(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();		
		
		mav.setViewName("interface/interface");
		return mav;
	}
	
	@RequestMapping(value="/interfaceList",method=RequestMethod.GET)
	@ResponseBody
	public String InterfaceList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=interfaceService.loadInterfaceList(dtp);	
		pagingData.setSEcho(dtp.sEcho);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;	
	}
	@RequestMapping(value="/interfaceinparam/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public String InterfaceinInfo(HttpServletRequest request,@PathVariable String ids,DataTableParamter dtp){		
		PagingData pagingData=interfaceParamService.loadInterfaceinparam(ids, dtp);
		pagingData.setSEcho(dtp.sEcho);	
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		else{
			Object[] aaData=pagingData.getAaData();
			for(int i=0;i<aaData.length;i++){
				InterfaceModel interfaceParam =new InterfaceModel();
				Param param=(Param)aaData[i];
				interfaceParam.setId(param.getId());
				interfaceParam.setName(param.getName());
				interfaceParam.setType(param.getType());
				interfaceParam.setDescription(param.getDescription());
				aaData[i]=interfaceParam;
			}
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	@RequestMapping(value="/interfaceoutparam/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public String InterfaceoutInfo(HttpServletRequest request,@PathVariable String ids,DataTableParamter dtp){		
		
		PagingData pagingData=interfaceParamService.loadInterfaceoutparam(ids, dtp);
		pagingData.setSEcho(dtp.sEcho);	
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		else{
			Object[] aaData=pagingData.getAaData();
			for(int i=0;i<aaData.length;i++){
				InterfaceModel interfaceParam =new InterfaceModel();
				Param param=(Param)aaData[i];
				interfaceParam.setId(param.getId());
				interfaceParam.setName(param.getName());
				interfaceParam.setType(param.getType());
				interfaceParam.setDescription(param.getDescription());
				aaData[i]=interfaceParam;
			}
		}
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
			
		}
	@RequestMapping(value="/interface/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public String DeleteInterfaceLog(HttpServletRequest request,@PathVariable String ids){
		String[] idstrArr=ids.split(",");		
				
		JSONObject respJson = new JSONObject();
		try{
			interfaceService.deleteInterface(idstrArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}

}
