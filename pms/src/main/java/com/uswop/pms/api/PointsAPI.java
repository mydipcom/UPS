/**
 * 
 */
package com.uswop.pms.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.apache.wink.common.annotations.Workspace;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uswop.pms.commons.MyApplicationContextUtil;
import com.uswop.pms.commons.SystemConfig;
import com.uswop.pms.commons.UswopException;
import com.uswop.pms.dto.TpointsHistory;
import com.uswop.pms.dto.TuserPoints;
import com.uswop.pms.service.PointsHistoryService;
import com.uswop.pms.service.UserPointsService;

/**
 * @author phills.li
 *
 */
@Workspace(workspaceTitle = "Uswop Middel Ware Service", collectionTitle = "Point Service")
@Path("point")
public class PointsAPI {
	private SystemConfig systemConfig =(SystemConfig)MyApplicationContextUtil.getContext().getBean("systemConfig");
	private UserPointsService userPointsService = (UserPointsService) MyApplicationContextUtil.getContext().getBean("userPointsService");
	private PointsHistoryService pointsHistoryService = (PointsHistoryService) MyApplicationContextUtil.getContext().getBean("pointsHistoryService");
	private Logger logger = Logger.getLogger(PointsAPI.class);
	
	@POST
	@Path("query")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPoints(String jsonStr,@HeaderParam("Authorization") String apiKey) {
		logger.info("Recived the query points request.");
		if(apiKey==null||!apiKey.equalsIgnoreCase(systemConfig.getApiKey())){
			return Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
		}
		JSONObject jsonObj=null;
		JSONObject respObj = new JSONObject();
		String responseStr="";
		try{
			jsonObj= (JSONObject)JSON.parse(jsonStr);
			String userid=jsonObj.getString("userId");
			if(userid==null||userid.isEmpty()){
				return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
			}
			TuserPoints userinfo=userPointsService.getUserInfoById(userid);
			int points=0;
			if(userinfo!=null){
				points=userinfo.getPoints();
			}
			
			respObj.put("status", true);
			respObj.put("info", "OK");
			respObj.put("points", points);
			responseStr= JSON.toJSONString(respObj);
		}
		catch(Exception e){
			respObj.put("status", false);
			respObj.put("info", e.getMessage());
			responseStr= JSON.toJSONString(respObj);
		}		
		return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
	}
		
	
	@POST
	@Path("deduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deductPoints(String jsonStr,@HeaderParam("Authorization") String apiKey) {
		if(apiKey==null||!apiKey.equalsIgnoreCase(systemConfig.getApiKey())){
			return Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
		}
		if(jsonStr == null) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
		}
		JSONObject jsonObj=null;
		JSONObject respObj = new JSONObject();
		String responseStr="";
		try{
			jsonObj= (JSONObject)JSON.parse(jsonStr);
			String userid=jsonObj.getString("userId");
			int deductPoints=jsonObj.getInteger("points");					
		
			userPointsService.deductPoints(userid, deductPoints);
			respObj.put("status", true);
			respObj.put("info", "OK");
			responseStr= JSON.toJSONString(respObj);								
			
		}		
		catch(Exception ex){
			respObj.put("status", false);
			respObj.put("info", ex.getMessage());
			responseStr= JSON.toJSONString(respObj);			
		}
		return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPoints(String jsonStr,@HeaderParam("Authorization") String apiKey) {
		if(apiKey==null||!apiKey.equalsIgnoreCase(systemConfig.getApiKey())){
			return Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
		}
		if(jsonStr == null) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
		}		
		JSONObject jsonObj= (JSONObject)JSON.parse(jsonStr);
		String userid=jsonObj.getString("userId");
		int deductPoints=jsonObj.getInteger("points");
		JSONObject jsonobj = new JSONObject();				
		try{
			userPointsService.addPoints(userid, deductPoints);
			jsonobj.put("status", true);
			String responseStr= JSON.toJSONString(jsonobj);
			return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		}
		catch(UswopException ue){
			jsonobj.put("status", false);
			jsonobj.put("info", ue.getMessage());
			String responseStr= JSON.toJSONString(jsonobj);
			return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		}
		catch(Exception ex){
			jsonobj.put("status", false);
			jsonobj.put("info", ex.getMessage());
			String responseStr= JSON.toJSONString(jsonobj);
			return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		}		
		
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newUser(String jsonStr,@HeaderParam("Authorization") String apiKey) {
		if(apiKey==null||!apiKey.equalsIgnoreCase(systemConfig.getApiKey())){
			return Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
		}
		if(jsonStr == null) {
			return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
		}		
		JSONObject jsonObj= (JSONObject)JSON.parse(jsonStr);
		String userid=jsonObj.getString("userId");
		int points=jsonObj.getInteger("points");
		JSONObject resObj = new JSONObject();				
		try{
			TuserPoints userInfo=new TuserPoints();
			userInfo.setUserId(userid);
			userInfo.setPoints(points);
			userPointsService.createUserInfo(userInfo);
			resObj.put("status", true);
			String responseStr= JSON.toJSONString(resObj);
			return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		}
		catch(UswopException ue){
			resObj.put("status", false);
			resObj.put("info", ue.getMessage());
			String responseStr= JSON.toJSONString(resObj);
			return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		}
		catch(Exception ex){
			resObj.put("status", false);
			resObj.put("info", ex.getMessage());
			String responseStr= JSON.toJSONString(resObj);
			return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
		}			
	}
	
	@POST
	@Path("history")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPointsHistory(String jsonStr,@HeaderParam("Authorization") String apiKey) {		
		if(apiKey==null||!apiKey.equalsIgnoreCase(systemConfig.getApiKey())){
			return Response.status(Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
		}
		
		JSONObject jsonObj=null;
		JSONObject respObj = new JSONObject();
		String responseStr="";
		try{		
			jsonObj= (JSONObject)JSON.parse(jsonStr);
			String userid=jsonObj.getString("userId");
			if(userid==null||userid.isEmpty()){
				return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
			}
			
			JSONObject resObj = new JSONObject();	
			List<TpointsHistory> list=pointsHistoryService.findPointsHistoryByStatus(userid,null);				
			resObj.put("status", true);
			resObj.put("info", "OK");		
			resObj.put("data", list);
			responseStr= JSON.toJSONString(resObj);
		}
		catch(Exception ex){
			respObj.put("status", false);
			respObj.put("info", ex.getMessage());
			responseStr= JSON.toJSONString(respObj);			
		}		
		return Response.ok(responseStr).type(MediaType.APPLICATION_JSON).build();
	}
	
	
}
