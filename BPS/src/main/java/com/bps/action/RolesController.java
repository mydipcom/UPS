/**   
 * @Title: RolesController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
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
import com.bps.commons.SystemConfig;
import com.bps.dto.TadminRole;
import com.bps.dto.TadminRoleRights;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminRoleRightsService;
import com.bps.service.AdminRoleService;

/**
 * @ClassName: RolesController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
public class RolesController extends BaseController {

	private Logger logger = Logger.getLogger(RolesController.class);

	@Resource
	private AdminRoleService adminRoleService;
	@Resource
	private AdminRoleRightsService adminRoleRightsService;
	
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	public ModelAndView roles(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.addObject("rightsList", SystemConfig.Admin_Nodes_Group_Map);		
		mav.setViewName("roles/roles");
		return mav;
	}
		
	@RequestMapping(value="/rolesList",method=RequestMethod.GET)
	@ResponseBody
	public String rolesList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=adminRoleService.loadAdminRolesList(dtp);
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
		pagingData.setSEcho(dtp.sEcho);
		
		String rightsListJson= JSON.toJSONString(pagingData);
		return rightsListJson;
	}
	
	/**
	 * <p>Description: 处理新增数据的ajax请求</p>
	 * @Title: addRole 
	 * @param jsonStr
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/addRole",method=RequestMethod.POST)
	@ResponseBody
	public String addRole(HttpServletRequest request,TadminRole adminRole){		
		JSONObject respJson = new JSONObject();
		try{
			adminRoleService.createAdminRole(adminRole);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/editRole",method=RequestMethod.POST)
	@ResponseBody
	public String updateRole(HttpServletRequest request,TadminRole adminRole){		

		JSONObject respJson = new JSONObject();
		try{
			adminRoleService.updateAdminRole(adminRole);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);		
	}
	
	@RequestMapping(value="/editRoleRights",method=RequestMethod.POST)
	@ResponseBody
	public String updateRoleRights(HttpServletRequest request,TadminRoleRights adminRoleRights){
		JSONObject respJson = new JSONObject();
		try{
			adminRoleRightsService.saveAdminRoleRights(adminRoleRights);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);		
	}
	

	@RequestMapping(value="/roles/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRoles(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			adminRoleService.deleteAdminRolesByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}
