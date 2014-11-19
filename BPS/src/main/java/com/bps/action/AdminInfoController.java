/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.bps.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bps.commons.BPSException;
import com.bps.commons.SecurityTools;
import com.bps.commons.SystemConstants;
import com.bps.dto.TadminInfo;
import com.bps.dto.TadminNodes;
import com.bps.dto.TadminUser;
import com.bps.model.ChangePasswordModel;
import com.bps.service.AdminInfoService;
import com.bps.service.AdminUserService;

@Controller
public class AdminInfoController extends BaseController {

	private Logger logger = Logger.getLogger(AdminInfoController.class);
	@Autowired
	private AdminInfoService adminInfoService;
	
    @Autowired
	private AdminUserService adminUserService;
    
	@RequestMapping(value="/userprofile",method=RequestMethod.GET)
	public ModelAndView user(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		TadminInfo adminInfo=new TadminInfo();
		try{
          TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
            if ( null == adminUser ){
             mav.setViewName("login");
	        }else{
			String adminId=adminUser.getAdminId();
			adminInfo = adminInfoService.getAdminInfoById(adminId);
			if(adminInfo == null){
				adminInfo = new TadminInfo();
				adminInfo.setAdminId(adminId);
				adminInfo.setGender(true);
				adminInfoService.createAdminInfo(adminInfo);
			}
			if(adminInfo.getGender()==null){
				adminInfo.setGender(true);
			}
	        }
        }catch(BPSException be){
        	
        }
		mav.addObject("adminInfo", adminInfo);
		mav.setViewName("userprofile/personal_info");
		return mav;
		
	}
	
	@RequestMapping(value="/editprofile",method=RequestMethod.POST)
	@ResponseBody
	public String editAdminInfo(HttpServletRequest request,TadminInfo adminInfo){
		JSONObject respJson = new JSONObject();
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			String adminId=adminUser.getAdminId();
			adminInfo.setAdminId(adminId);
			adminInfoService.updateAdminInfo(adminInfo);
			respJson.put("status", true);
		}catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}
		return JSON.toJSONString(respJson);
	   }
	 
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(HttpServletRequest request,ChangePasswordModel cpMod){
		JSONObject respJson = new JSONObject();
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			if(!SecurityTools.SHA1(cpMod.getOldpassword()).equals(adminUser.getPassword())){
				respJson.put("status", true);
				respJson.put("olderror",true);
			}
			else{
				request.getSession().removeAttribute(SystemConstants.LOGINED);
				adminUser.setPassword(SecurityTools.SHA1(cpMod.getNewpassword()));
				adminUserService.updateAdminUserPassword(adminUser);
				respJson.put("status", true);
			}
			
		}catch(BPSException be){
			respJson.put("status", false);
			respJson.put("info", be.getMessage());
		}
		return JSON.toJSONString(respJson);
	   }
	
	@RequestMapping(value="/userprofile/chageAvatar",method=RequestMethod.POST)
	
	public String changeAvatar(HttpServletRequest request,@RequestParam(value = "avatar", required = false) MultipartFile file) throws IOException{
		JSONObject resp = new JSONObject();
		InputStream inputStream = file.getInputStream();
		byte [] avatar=new byte[1048576];
		inputStream.read(avatar);
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			TadminInfo adminInfo = new TadminInfo();
			String adminId=adminUser.getAdminId();
			adminInfo=adminInfoService.getAdminInfoById(adminId);
			adminInfo.setAvatar(avatar);
			adminInfoService.updateAdminInfoAvatar(adminInfo);
			resp.put("status", true);
		}catch(BPSException be){
			resp.put("status", false);
			resp.put("info", be.getMessage());
		}
		
		//return JSON.toJSONString(resp);
		
		return "redirect:/userprofile"; 
	   }
      
	@RequestMapping(value="/userprofile/getAvatar",method=RequestMethod.GET)
	public void getAvatar(HttpServletRequest request,HttpServletResponse response){
		try{
			TadminUser adminUser = (TadminUser) request.getSession().getAttribute(SystemConstants.LOGINED);
			TadminInfo adminInfo = new TadminInfo();
			String adminId=adminUser.getAdminId();
			adminInfo=adminInfoService.getAdminInfoById(adminId);
			//设置页面不缓存
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			ByteArrayInputStream bin;
			if(adminInfo.getAvatar() == null){
				File file = new File(request.getSession().getServletContext().getRealPath("/")+File.separator+"static"+File.separator+"images"+File.separator+"profile.jpg");
			    FileImageInputStream inputStream = new FileImageInputStream(file);
				byte [] avatar=new byte[1048576];
				inputStream.read(avatar);
				bin = new ByteArrayInputStream(avatar);
			 }else{
				bin = new ByteArrayInputStream(adminInfo.getAvatar()); 
			 }
			 BufferedImage buffImage=ImageIO.read(bin);
			 ImageIO.write(buffImage, "JPEG", response.getOutputStream());
		}catch(BPSException b){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
