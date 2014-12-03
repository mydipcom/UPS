package com.bps.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bps.commons.ClassTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.ConvertTools;
import com.bps.dto.Param;
import com.bps.dto.TInterface;
import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.InterfaceInfoAnnotation;
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
		try {
			List<List<String>>  interfaceInfos = new ArrayList<List<String>>();
			List<String> interfaceInfo = null;
			List<Class> clazzs = ClassTools.getClassByPackage("com.bps.api");
			if(clazzs != null){
				for(Class clazz:clazzs){
					    Method []methods = clazz.getDeclaredMethods();
						InterfaceInfoAnnotation annotation;
						for(Method method : methods){
							annotation=method.getAnnotation(InterfaceInfoAnnotation.class);
							interfaceInfo = new ArrayList<String>();
							interfaceInfo.add(annotation.name());
							interfaceInfo.add(annotation.description());
							interfaceInfos.add(interfaceInfo);
							}
				}
				mav.addObject("interfaces", interfaceInfos);
			}
			
		  
			
		}catch(BPSException b){
			
		}catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.setViewName("interface/interface");
		return mav;
	}
	
	@RequestMapping(value="/interfaceInfo",method=RequestMethod.GET)
	public ModelAndView interfaceInfo(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		try {
			List<List<String>>  interfaceInfos = new ArrayList<List<String>>();
			List<String> interfaceInfo = null;
			
			
			Method []methods = Class.forName("com.bps.api.PointAPI").getDeclaredMethods();
			InterfaceInfoAnnotation annotation;
			for(Method method : methods){
				annotation=method.getAnnotation(InterfaceInfoAnnotation.class);
				interfaceInfo = new ArrayList<String>();
				interfaceInfo.add(annotation.name());
				interfaceInfo.add(annotation.description());
				interfaceInfos.add(interfaceInfo);
				
			}
			mav.addObject("interfaces", interfaceInfos);
		}catch(BPSException b){
			
		}catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("interfaceInfo/interfaceInfo");
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
	public String DeleteInterface(HttpServletRequest request,@PathVariable String ids){
		String[] idstrArr=ids.split(",");		
				
		JSONObject respJson = new JSONObject();
		try{
			interfaceParamService.deleteInterfaceParam(idstrArr);
			interfaceService.deleteInterface(idstrArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/addinterface", method=RequestMethod.POST)
	@ResponseBody
	public String AddInterface(HttpServletRequest request,TInterface interfaces){
					
		JSONObject respJson = new JSONObject();
		try{
			interfaceService.createInterface(interfaces);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/editinterface", method=RequestMethod.POST)
	@ResponseBody
	public String EditInterface(HttpServletRequest request,TInterface interfaces){
					
		JSONObject respJson = new JSONObject();
		try{
			interfaceService.updateInterface(interfaces);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	@RequestMapping(value="/addinterfaceparam", method=RequestMethod.POST)
	@ResponseBody
	public String AddInterfaceParam(HttpServletRequest request,Param param){
		JSONObject respJson = new JSONObject();
		try{
			interfaceParamService.createInterfaceParam(param);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}
