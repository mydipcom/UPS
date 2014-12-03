package com.bps.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.ExcelTools;
import com.bps.commons.LogManageTools;
import com.bps.dto.TadminLog;
import com.bps.dto.TpointUser;
import com.bps.dto.TpointsLog;
import com.bps.model.ChangePointModel;
import com.bps.model.DataTableParamter;
import com.bps.model.PagingData;
import com.bps.service.PointUserService;
import com.sun.istack.logging.Logger;

@Controller
public class PointController extends BaseController {
	
     private Logger log = Logger.getLogger(PointController.class);
     
     @Autowired
     private PointUserService pointUserService;
     
     private String log_content;
     
     @RequestMapping(value="/point",method = RequestMethod.GET)
     public ModelAndView point(HttpServletRequest request){
    	 ModelAndView mav = new ModelAndView();    	 
    	 mav.setViewName("/point/pointManagement");
    	 return mav;
     }
     
     @RequestMapping(value="/pointsList",method=RequestMethod.GET)
     @ResponseBody
     public String pointsList(HttpServletRequest request,DataTableParamter dtp){
    	 PagingData pagingData = pointUserService.loadPoitUsersList(dtp);
    	 pagingData.setSEcho(dtp.getsEcho());
    	 if(pagingData.getAaData()==null){
 			Object[] objs=new Object[]{};
 			pagingData.setAaData(objs);
 		}
    	 return JSON.toJSONString(pagingData);
     }
     
     @RequestMapping(value="/management_points/{ids}",method=RequestMethod.POST)
     @ResponseBody
     public String managementPointUsers(HttpServletRequest request,@PathVariable String ids,@RequestParam(value="act", required=true)String act){
    	 JSONObject respJson = new JSONObject();
    	 try{
    		 String []user_id=ids.split(",");
    		 if(Integer.parseInt(act)==1){
        	 pointUserService.updateUserStatus(user_id, true);
        	 }else{
        		 pointUserService.updateUserStatus(user_id,false);
        	 }
        	 respJson.put("status", true);
    	 }catch(BPSException b){
    		 respJson.put("status", false);
    		 respJson.put("info", b.getMessage());
    	 }
    	 return JSON.toJSONString(respJson);
     }
     
     @RequestMapping(value="/changeUserPoints",method=RequestMethod.POST)
     @ResponseBody
     public String changerUserPoints(HttpServletRequest request,ChangePointModel cpm,HttpServletResponse response){
    	 JSONObject respJson = new JSONObject();
    	 TpointUser pointUser = new TpointUser();
    	 TpointsLog pointsLog = new TpointsLog();
    	 TadminLog  adminLog = new TadminLog();
    	try{
         pointUser  = pointUserService.getUserInfoById(cpm.getUserId());
         pointsLog.setPointUser(pointUser);
          int curentPoints=pointUser.getPoints();
          if(cpm.getAction() == 1){
           pointUser.setPoints(curentPoints+cpm.getAmount());
           pointsLog.setPoints(cpm.getAmount());
           pointsLog.setPointsBalance(pointUser.getPoints());
          }else{
        	  curentPoints=curentPoints-cpm.getAmount();
        	  if(curentPoints<0)
        	  {
        		  curentPoints=0;
        	  }
              pointUser.setPoints(curentPoints);
              pointsLog.setPoints(-cpm.getAmount());
              pointsLog.setPointsBalance(pointUser.getPoints());
            }
             log_content="success:change points.";
             pointUserService.updateUserInfo(pointUser);
          	 respJson.put("status", true);
    	 }catch(BPSException b){
    		 respJson.put("status", false);
    		 respJson.put("info", b.getMessage());
    		
    	 }
    	 pointsLog.setFrom(0);
    	 LogManageTools.writePointLog(log_content, pointsLog);
    	 adminLog.setAdminId(getSessionUser(request).getAdminId());
    	 LogManageTools.writeAdminLog(log_content, adminLog);
    	 return JSON.toJSONString(respJson);
     }
     
     @RequestMapping(value="/changeMoreUserPoints/{ids}",method=RequestMethod.POST)
     @ResponseBody
     public String changerMoreUserPoints(HttpServletRequest request,ChangePointModel cpm,@PathVariable String ids,HttpServletResponse response){
    	 JSONObject respJson = new JSONObject();
    	 TadminLog adminLog = new TadminLog();
    	 TpointsLog pointsLog;
    	 try{
    	 String []user_ids = ids.split(",");
    	 for(String user_id:user_ids){
    		 pointsLog = new TpointsLog();
    		 TpointUser pointUser  = pointUserService.getUserInfoById(user_id);
    		  pointsLog.setPointUser(pointUser);
               int curentPoints=pointUser.getPoints();
               if(cpm.getAction() == 1){
                    pointUser.setPoints(curentPoints+cpm.getAmount());
                    pointsLog.setPoints(cpm.getAmount());
                    pointsLog.setPointsBalance(pointUser.getPoints());
               }else{
        	        curentPoints=curentPoints-cpm.getAmount();
        	        if(curentPoints<0)
        	        {
        		      curentPoints=0;
        	        }
                    pointUser.setPoints(curentPoints);
                    pointsLog.setPoints(-cpm.getAmount());
                    pointsLog.setPointsBalance(pointUser.getPoints());
               }
                pointUserService.updateUserInfo(pointUser);
                log_content="success:change points.";
                LogManageTools.writePointLog(log_content, pointsLog);
                
    	     }
          	 respJson.put("status", true);
    	 }catch(BPSException b){
    		 respJson.put("status", false);
    		 respJson.put("info", b.getMessage());
    		
    	 }
    	 adminLog.setAdminId(getSessionUser(request).getAdminId());
    	 LogManageTools.writeAdminLog(log_content, adminLog);
    	 return JSON.toJSONString(respJson);
     }
     
     @RequestMapping(value="/importPointUser",method=RequestMethod.POST)
     @ResponseBody
     public String importPointUserByExcel(HttpServletRequest request,@RequestParam(value="pointuserexcel",required=true)MultipartFile file){
    	JSONObject resp = new JSONObject();
        try {
        	String fileName = file.getOriginalFilename();
        	fileName = fileName.toLowerCase();
        	if(!(fileName.endsWith(".xls")||fileName.endsWith(".xlsx"))){
        		resp.put("status", false);
        		resp.put("info", "You have some form errors. Please import a excel file.");
        		return JSON.toJSONString(resp);
        	}
    		List<String[]> datas = ExcelTools.readExcelPOI(file, 0);
    		for(int i=0;i<datas.size();i++){
    			TpointUser pointUser = new TpointUser();
    			String data [] = datas.get(i);
    			if(data.length != 2){
    				continue;
    			}
    			if(data[0] != null){
    				pointUser = pointUserService.getUserInfoById(data[0]);
    				if(pointUser != null){
    					 pointUser.setPoints(Integer.parseInt(data[1]));
    				     pointUserService.updateUserInfo(pointUser);
    				     }
    				else{
    					 pointUser = new TpointUser();
    					 pointUser.setUserId(data[0]);
    					 pointUser.setPoints(Integer.parseInt(data[1]));
    					 pointUserService.createUserInfo(pointUser);
    				}
    			}else{
    				continue;
    			}
    			}
    		 resp.put("status", true);
		}catch(BPSException b){
			resp.put("status", false);
			resp.put("info", "You have some form errors. Please check below.");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			resp.put("status", false);
			resp.put("info", "You have some form errors. Please check below.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			resp.put("status", false);
			resp.put("info", "You have some form errors. Please check below.");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resp.put("status", false);
			resp.put("info", "You have some form errors. Please check below.");
			e.printStackTrace();
		} 
        
    	
		return JSON.toJSONString(resp);
    	 
     }
     
     @RequestMapping(value="/addPointUser",method=RequestMethod.POST)
     @ResponseBody
     public String addPointUser(HttpServletRequest request,String userId,int points,Boolean statics){
    	 TpointUser pointUser = pointUserService.getUserInfoById(userId);
    	 JSONObject resp = new JSONObject();
    	 try{
    		
        	 if(pointUser == null){
             pointUser=new TpointUser();
        	 pointUser.setUserId(userId);
        	 pointUser.setPoints(points);
        	 pointUser.setStatus(statics);
        	 pointUserService.createUserInfo(pointUser);
    		 resp.put("status", true);
        	 }else{
        		 resp.put("status", false);
        		 resp.put("info", "User Id is exist.");
        	 }
    		
    	 }catch(BPSException b){
    		 resp.put("status", false);
    	 }
    	 
    	 return JSON.toJSONString(resp);
     }
}
