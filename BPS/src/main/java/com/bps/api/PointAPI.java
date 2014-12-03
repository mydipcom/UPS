package com.bps.api;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.IpUtils;
import com.bps.commons.LogManageTools;
import com.bps.commons.SystemConfig;
import com.bps.dto.TInterfaceLog;
import com.bps.dto.TpointUser;
import com.bps.model.InterfaceInfoAnnotation;
import com.bps.service.PointUserService;

@RestController
@RequestMapping(value="/api")
public class PointAPI {
	
	@Autowired
	private PointUserService pointUserService;
	
	private String log_content;
	
	@RequestMapping(value="/query")
	@InterfaceInfoAnnotation(name="query",description="Query Describe")
	public String getPointUserApi(HttpServletRequest request ,@RequestHeader("Authorization") String apiKey,@RequestParam String user_id){
		JSONObject resp = new JSONObject();
		TInterfaceLog interfaceLog = new TInterfaceLog();
		interfaceLog.setName("query");
		interfaceLog.setAccessBy(IpUtils.getIpAddr(request));
		log_content="success:interface access.";
		if(apiKey ==null || !apiKey.equalsIgnoreCase(SystemConfig.Api_Access_Key)){
			resp.put("success", 0);
			resp.put("msg", "apikey does not exist or error");
			log_content="error: does not exist or error.";
			LogManageTools.writeInterfaceLog(log_content, interfaceLog);
			return JSON.toJSONString(resp);
		}
		TpointUser pointUser = pointUserService.getUserInfoById(user_id);
		if(pointUser == null){
			resp.put("success", 0);
			resp.put("msg", "userid does not exist or error");
			log_content="error:userid does not exist or error.";
			LogManageTools.writeInterfaceLog(log_content, interfaceLog);
			return JSON.toJSONString(resp);
		}
		resp.put("success", 1);
		resp.put("results", pointUser);
		LogManageTools.writeInterfaceLog(log_content, interfaceLog);
		return JSON.toJSONString(resp);
	}
	
	@RequestMapping(value="/changebonus",method=RequestMethod.POST)
	@InterfaceInfoAnnotation(name="changebonus",description="Changebonus Describe")
	public String changeBonusApi(HttpServletRequest request,@RequestHeader("Authorization")String apiKey,@RequestBody String jsonStr){
		JSONObject resp = new JSONObject();
		TInterfaceLog interfaceLog = new TInterfaceLog();
		interfaceLog.setName("changebonus");
		interfaceLog.setAccessBy(IpUtils.getIpAddr(request));
		log_content="error:interface error.";
		TpointUser pointUser = new TpointUser();
		if(apiKey ==null || !apiKey.equalsIgnoreCase(SystemConfig.Api_Access_Key)){
			resp.put("success", 0);
			resp.put("msg", "apikey does not exist or error.");
			return JSON.toJSONString(resp);
		}
		try{
			if(jsonStr==null || jsonStr.isEmpty()){
				resp.put("success", 0);
				resp.put("msg", "params error.");
				LogManageTools.writeInterfaceLog(log_content, interfaceLog);
				return JSON.toJSONString(resp);
			}
			JSONObject json = (JSONObject) JSON.parse(jsonStr);
			if(json.getString("userId") == null || json.getString("userId").isEmpty() || json.getString("acount") == null || json.getString("action") == null){
				resp.put("success", 0);
				resp.put("msg", "params error.");
				LogManageTools.writeInterfaceLog(log_content, interfaceLog);
				return JSON.toJSONString(resp);
			}
			pointUser = pointUserService.getUserInfoById(json.getString("userId"));
			if( pointUser == null ){
				resp.put("success", 0);
				resp.put("msg", "userId does not exist or error.");
				LogManageTools.writeInterfaceLog(log_content, interfaceLog);
				return JSON.toJSONString(resp);
			}
			 if(!pointUser.isStatus())
			 {
				    resp.put("success", 0);
					resp.put("msg", "the user has been disabled.");
					LogManageTools.writeInterfaceLog(log_content, interfaceLog);
					return JSON.toJSONString(resp);
			 }
			 int curentPoints=pointUser.getPoints();
	          if( json.getIntValue("action") == 1){
	           pointUser.setPoints(curentPoints+json.getIntValue("acount"));
	          }else{
	        	  curentPoints=curentPoints-json.getIntValue("acount");
	        	  if(curentPoints<0)
	        	  {
	        		  curentPoints=0;
	        	  }
	              pointUser.setPoints(curentPoints);
	            }
	             pointUserService.updateUserInfo(pointUser);
	             resp.put("success", 1);
				 resp.put("results", pointUser);
		}catch(BPSException b){
			resp.put("success", 0);
			resp.put("msg", "some error.");
			return JSON.toJSONString(resp);
		}
		log_content="success:interface access.";
		LogManageTools.writeInterfaceLog(log_content, interfaceLog);
		return JSON.toJSONString(resp);
	}

}
