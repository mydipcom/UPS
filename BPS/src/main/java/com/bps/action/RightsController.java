/**   
 * @Title: RightsController.java 
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
import com.bps.dto.TadminNodes;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
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
	
	@Resource
	private AdminNodesService adminNodesService;
		

	@RequestMapping(value="/rights",method=RequestMethod.GET)
	public ModelAndView rights(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		
//		mav.addObject("user", tUser);
		mav.setViewName("rights/rights");
		return mav;
	}
	
	@RequestMapping(value="/rightsList",method=RequestMethod.GET)
	@ResponseBody
	public String rightsList(HttpServletRequest request,DataTableParamter dtp){		
		PagingData pagingData=adminNodesService.loadAdminNodesList(dtp);
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
	 * @Title: addRights 
	 * @param jsonStr
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/addRights",method=RequestMethod.POST)
	@ResponseBody
	public String addRights(HttpServletRequest request,TadminNodes adminNode){
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
			adminNodesService.createAdminNode(adminNode);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}		
		return JSON.toJSONString(respJson);
	}
	
	@RequestMapping(value="/editRights",method=RequestMethod.POST)
	@ResponseBody
	public String updateRights(HttpServletRequest request,TadminNodes adminNode){		
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
			adminNodesService.updateAdminNode(adminNode);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);		
	}

	@RequestMapping(value="/rights/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteRights(@PathVariable String ids,HttpServletRequest request){
		String[] idstrArr=ids.split(",");		
		Integer[] idArr=ConvertTools.stringArr2IntArr(idstrArr);		
		JSONObject respJson = new JSONObject();
		try{
			adminNodesService.deleteAdminNodesByIds(idArr);
			respJson.put("status", true);
		}
		catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}	
		return JSON.toJSONString(respJson);	
	}
}
