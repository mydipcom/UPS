package com.bps.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.InterfaceInfoAnnotation;
import com.bps.model.InterfaceModel;
import com.bps.model.InterfaceParamAnnotation;
import com.bps.model.PagingData;


@Controller
public class InterfaceController extends BaseController{
	
	
	@RequestMapping(value="/interface",method=RequestMethod.GET)
	public ModelAndView interfaces(HttpServletRequest request,String id){
		ModelAndView mav=new ModelAndView();
		try {
			Map<String,List<String>> interfaces = new LinkedHashMap<String, List<String>>(); 
			Map<String,List<List<String>>> interfaceInputParam = new LinkedHashMap<String, List<List<String>>>(); 
			Map<String,List<List<String>>> interfaceOutParam = new LinkedHashMap<String, List<List<String>>>();
			List <String> list=null;
			List<List<String>> inParamList=null;
			List<List<String>> outParamList=null;
			List<Class> clazzs = ClassTools.getClassByPackage("com.bps.api");
			if(clazzs != null){
				for(Class clazz:clazzs){
					    Method []methods = clazz.getDeclaredMethods();
						InterfaceInfoAnnotation annotation;
						InterfaceParamAnnotation annotationParam;
						for(Method method : methods){
							    list = new ArrayList<String>();
							    annotation=method.getAnnotation(InterfaceInfoAnnotation.class);
								list.add(annotation.name());
								list.add(annotation.url());
								list.add(annotation.description());
								interfaces.put(annotation.name(), list);
								annotationParam = method.getAnnotation(InterfaceParamAnnotation.class);
							    String []params = annotationParam.param();
							    inParamList=new ArrayList<List<String>>();
						    	outParamList=new ArrayList<List<String>>();
							    for(String p : params){
							    	String param[]=p.split("@");
							    	list = new ArrayList<String>();
							    	for(int i=0;i<4;i++){
							    	   list.add(param[i]);
							    	 }
							    	if("input".equals(param[4])){
							    		inParamList.add(list);
							    	}else if("output".equals(param[4])){
							    		outParamList.add(list);
							    	}else{
							    		inParamList.add(list);
							    		outParamList.add(list);
							    	}
							    }
								interfaceInputParam.put(annotation.name(), inParamList);
								interfaceOutParam.put(annotation.name(), outParamList);
							    }
				}
				
			}
			mav.addObject("interfaces", interfaces);
			mav.addObject("interfaceInputParam", interfaceInputParam);
			mav.addObject("interfaceOutParam", interfaceOutParam);
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
	
	
	

}
