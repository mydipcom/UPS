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
import com.bps.dto.TadminUser;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.AdminNodesService;
import com.bps.service.AdminUserService;

@Controller
public class ManagerController extends BaseController {
private Logger logger = Logger.getLogger(UserController.class);
	
	
	@Resource
	private AdminUserService adminUserService;
	
	@Resource
	private AdminNodesService adminNodesService;
	
	
	@RequestMapping(value="/manager",method=RequestMethod.GET)
	public ModelAndView adminusers(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		
//		mav.addObject("user", tUser);
		mav.setViewName("manager/Adminusers");
		return mav;
	}
	
	@RequestMapping(value="/managersList",method=RequestMethod.GET)
	@ResponseBody
	public String AdminusersList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=adminUserService.loadAdminUserList(dtp);
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
	@RequestMapping(value="/addUsers",method=RequestMethod.POST)
	@ResponseBody
	public String addAdmins(HttpServletRequest request,TadminUser adminuser){
//		JSONObject jsonObj= (JSONObject)JSON.parse(jsonStr);		
//		TadminNodes adminNode=new TadminNodes();
//		ConvertTools.json2Model(jsonObj, adminNode);
//		adminNode.setName(jsonObj.getString("name"));
//		adminNode.setUri(jsonObj.getString("uri"));
//		adminNode.setMethod(jsonObj.getString("method"));
//		adminNode.setPid(jsonObj.getIntValue("pid"));
//		adminNode.setIsMenu(jsonObj.getBooleanValue("isMenu"));
//		adminNode.setGroupName(jsonObj.getString("groupName"));
//		adminNode.setGroupSort(jsonObj.getShortValue("groupSort"));
//		adminNode.setDescr(jsonObj.getString("descr"));
//		adminNode.setStatus(jsonObj.getBooleanValue("status"));
			
		JSONObject respJson = new JSONObject();
		try{
			adminUserService.createAdminUser(adminuser);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/editUsers",method=RequestMethod.POST)
	@ResponseBody
	public String updateAdmin(HttpServletRequest request,TadminUser adminuser){		
//		TadminNodes adminNode=new TadminNodes();		
//		JSONObject jsonObj= (JSONObject)JSON.parse(jsonStr);
//		ConvertTools.json2Model(jsonObj, adminNode);
//		adminNode.setName(jsonObj.getString("name"));
//		adminNode.setUri(jsonObj.getString("uri"));
//		adminNode.setMethod(jsonObj.getString("method"));
//		adminNode.setPid(jsonObj.getIntValue("pid"));
//		adminNode.setIsMenu(jsonObj.getBooleanValue("isMenu"));
//		adminNode.setGroupName(jsonObj.getString("groupName"));
//		adminNode.setGroupSort(jsonObj.getShortValue("groupSort"));
//		adminNode.setDescr(jsonObj.getString("descr"));
//		adminNode.setStatus(jsonObj.getBooleanValue("status"));

		JSONObject respJson = new JSONObject();
		try{
			adminUserService.updateAdminUser(adminuser);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		String str=JSON.toJSONString(respJson);		
		return str;
	}

	@RequestMapping(value="/managers/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteAdmins(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
	//	Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			adminUserService.deleteAdminUserByIds(idstrArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}

}
