package com.bps.action;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.bps.commons.SecurityTools;
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
		Object[] aaData=pagingData.getAaData();
		for(int i=0;i<aaData.length;i++){
			TadminUser adminuser=(TadminUser)aaData[i];
			if(adminuser.getCreatedBy()==null){
				adminuser.setCreatedBy("");
				adminuser.setCreatedTimeStr("");
			}
			if(adminuser.getUpdatedBy()==null){
				adminuser.setUpdatedBy("");
				adminuser.setUpdatedTimeStr("");
			}
			aaData[i]=adminuser;
		}
		if(pagingData.getAaData()==null){
			Object[] objs=new Object[]{};
			pagingData.setAaData(objs);
		}
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
		TadminUser ad=getSessionUser(request);
		JSONObject respJson = new JSONObject();
		try{
			adminuser.setCreatedBy(ad.getAdminId());
			adminuser.setPassword(SecurityTools.SHA1(adminuser.getPassword()));
			adminuser.setCreatedTime(System.currentTimeMillis());
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
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		TadminUser ad=getSessionUser(request);
		JSONObject respJson = new JSONObject();
		Date sdate = null;
		try{
			try {
				String ss=adminuser.getCreatedTimeStr();
				sdate = simpleDateFormat.parse(ss);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			adminuser.setCreatedTime(sdate.getTime());
			adminuser.setUpdatedBy(ad.getAdminId());
			adminuser.setUpdatedTime(System.currentTimeMillis());
			adminuser.setPassword(SecurityTools.SHA1(adminuser.getPassword()));
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
	@RequestMapping(value="/activateusers/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String activateRules(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
	
		JSONObject respJson = new JSONObject();
		try{
			adminUserService.activateUsersByIds(idstrArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
	
	@RequestMapping(value="/deactivateusers/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String deactivateRules(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");				
		JSONObject respJson = new JSONObject();
		try{
			adminUserService.deactivateUsersByIds(idstrArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}

}
