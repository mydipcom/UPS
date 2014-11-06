/**   
 * @Title: RightsController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.bps.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bps.dto.TadminNodes;
import com.bps.model.PagingData;
import com.bps.model.RightsDataTableParamter;
import com.bps.service.AdminNodesService;

/**
 * @ClassName: RightsController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
public class RightsController extends BaseController {

	private Logger logger = Logger.getLogger(RightsController.class);
	
	@Autowired
	private AdminNodesService adminNodesService;			

	@RequestMapping(value="/rights",method=RequestMethod.GET)
	public ModelAndView rights(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		
//		mav.addObject("user", tUser);
		mav.setViewName("rights/rights");
		return mav;
	}
	
	@RequestMapping(value="/rightsList",method=RequestMethod.POST)
	@ResponseBody
	public String rightsList(HttpServletRequest request,RightsDataTableParamter dtp,ModelMap model){		
		PagingData pagingData=adminNodesService.loadAdminNodesList(dtp);
		pagingData.setSEcho(dtp.sEcho);
//		pagingData.setITotalDisplayRecords(1);
//		pagingData.setITotalRecords(1);
//		TadminNodes ad=new TadminNodes();
//		ad.setDescr("desc test");
//		ad.setGroupName("Rights");
//		ad.setGroupSort((short)1);
//		ad.setIsMenu(true);
//		ad.setName("Rights");
//		ad.setNodeId(1);
//		ad.setPid(0);
//		ad.setStatus(true);
//		ad.setUri("/rightsList");
//		pagingData.setAaData(new Object[]{ad});
//				
//		model.addAttribute("rightsList", pagingData);
		
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
	}
	
	/**
	 * <p>Description:添加权限</p>
	 * @Title: rights 
	 * @param id
	 * @param request
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/rights/{id}",method=RequestMethod.GET)
	public ModelAndView rights(@PathVariable int id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("rights/rights");
		return mav;
	}
	
	@RequestMapping(value="/rights",method=RequestMethod.POST)
	public ModelAndView addRights(HttpServletRequest request,TadminNodes adminNodes){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("rights/rights");
		return mav;
	}
	
	@RequestMapping(value="/rights/{id}",method=RequestMethod.PUT)
	public ModelAndView updateRights(HttpServletRequest request,TadminNodes adminNodes){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("rights/rights");
		return mav;
	}

	@RequestMapping(value="/rights/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteRights(@PathVariable int id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("rights/rights");
		return mav;
	}
}
