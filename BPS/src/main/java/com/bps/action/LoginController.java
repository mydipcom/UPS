/**   
* @Title: LoginController.java 
* @Package com.uswop.action 
*
* @Description: TODO
* 
* @date Sep 10, 2014 3:06:32 PM
* @version V1.0   
*/ 
package com.bps.action;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bps.commons.EMailTool;
import com.bps.commons.LogManageTools;
import com.bps.commons.SecurityTools;
import com.bps.commons.SystemConfig;
import com.bps.commons.SystemConstants;
import com.bps.dto.TadminLog;
import com.bps.dto.TadminUser;
import com.bps.dto.TemaiMessage;
import com.bps.service.AdminUserService;
import com.bps.service.AdminuserLogService;

/** 
 * @ClassName: LoginController 
 * @Description: 登录请求处理控制器
 * @author Phills Li
 * @date Sep 10, 2014 3:06:32 PM 
 *  
 */
@Controller
public class LoginController extends BaseController {	
	@Autowired
	private AdminUserService adminUserService;		
	
    @Autowired
    private AdminuserLogService adminuserLogService;
	/** 
	 * <p>Open the login page</p>
	 * @Title: login 
	 * @return String
	 * @throws 
	 */ 
    private String log_content;
    
    @RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=new TadminUser();		
		mav.addObject("user", tUser);
		mav.setViewName("login");
		return mav;
	}
		
	
	/** 
	 * <p>User Login</p>
	 * @Title: login 
	 * @param request
	 * @param user
	 * @return ModelAndView
	 * @throws 
	 */ 
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,TadminUser user){
		TadminLog adminLog = new TadminLog();
		adminLog.setAdminId(user.getAdminId());
		TadminUser tUser=(TadminUser)adminUserService.getAdminUserById(user.getAdminId());
		ModelAndView mav=new ModelAndView();
		Long time = (Long) request.getSession().getAttribute(SystemConstants.LOGIN_STATUS);
		if(time != null && System.currentTimeMillis()-time<60000*Integer.parseInt(SystemConfig.Admin_Setting_Map.get(SystemConstants.LOGIN_ERROR_LOCK))){
			    mav.addObject(ERROR_MSG_KEY, "The password is wrong too many times account is locked for "+Integer.parseInt(SystemConfig.Admin_Setting_Map.get(SystemConstants.LOGIN_ERROR_LOCK))+" minutes.");
				if(tUser != null){
				  mav.addObject("user", tUser);
				}else{
					mav.addObject("user", new TadminUser());
				}
				adminLog.setLevel((short)1);
				log_content=SystemConstants.LOG_FAILURE+":userid locked.";
		}
		else if(tUser==null){
			mav.addObject(ERROR_MSG_KEY, " Login failed (Username/Password refused) .");
			mav.addObject("user", new TadminUser());
			saveLoginErrorTims(request);
			adminLog.setLevel((short)1);
			log_content=SystemConstants.LOG_FAILURE+":userid error";
		}
		else if(!SecurityTools.SHA1(user.getPassword()).equalsIgnoreCase(tUser.getPassword())){
			mav.addObject(ERROR_MSG_KEY, " Login failed (Username/Password refused). ");
			mav.addObject("user", tUser);
			saveLoginErrorTims(request);
			adminLog.setLevel((short)1);
			log_content=SystemConstants.LOG_FAILURE+":password error.";
		}else{
			setSessionUser(request, tUser);
			String toUrl=(String)request.getSession().getAttribute(LOGIN_TO_URL);
			request.getSession().removeAttribute(LOGIN_TO_URL);
			request.getSession().removeAttribute(SystemConstants.LOGIN_ERROR);
			request.getSession().removeAttribute(SystemConstants.LOGIN_STATUS);
			if(StringUtils.isEmpty(toUrl)&&tUser.getAdminRole().getRoleId()==1){
				toUrl="/home";
			}else if (StringUtils.isEmpty(toUrl)&&tUser.getAdminRole().getRoleId()!=1) {
				toUrl="/point";	
			}			
			mav.setViewName("redirect:"+toUrl);
			log_content=SystemConstants.LOG_SUCCESS+":login success.";
			
		}
		LogManageTools.writeAdminLog(log_content,adminLog);
		return mav;
	}
	
	@RequestMapping(value="/ResetPassword",method=RequestMethod.POST)
	public ModelAndView resetPassword(HttpServletRequest request ,String email){
		ModelAndView mav = new ModelAndView();
		TadminLog adminLog = new TadminLog();
		TadminUser adminUser = adminUserService.getTadminUsersByEmail(email.toLowerCase());
		if(adminUser == null){
			 adminUser = new TadminUser();
			//邮箱不存在
			 mav.addObject(ERROR_MSG_KEY,"email not exist.");
		}else{
			adminLog.setAdminId(adminUser.getAdminId());
		   
			String random = UUID.randomUUID().toString().trim().replace("-","").substring(0,6);
			adminUser.setPassword(SecurityTools.SHA1(random));
			adminUser.setUpdatedBy(adminUser.getAdminId());
			adminUser.setUpdatedTime(System.currentTimeMillis());
			TemaiMessage message = new TemaiMessage();
			message.setTo(email);
			message.setText("你的BPS帐号："+adminUser.getAdminId()+"于"+Calendar.getInstance().getTime()+"重置密码，新密码为："+random);
			message.setSubject("BPS重置密码");
			EMailTool.send(message);
			adminUserService.updateAdminUserPassword(adminUser);
			mav.addObject(ERROR_MSG_KEY,"password reset success.");
			LogManageTools.writeAdminLog(log_content, adminLog);
		}
		mav.addObject("user",adminUser);
		mav.setViewName("login");
		log_content="success:resert password success.";
		
		return mav;
	}
	
	/** 
	 * <p>注销登录用户</p>
	 * @Title: logout 
	 * @param session
	 * @return String
	 * @throws 
	 */ 
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		TadminLog adminLog = new TadminLog();
		if((TadminUser)session.getAttribute(SystemConstants.LOGINED)!=null){
		adminLog.setAdminId(((TadminUser)session.getAttribute(SystemConstants.LOGINED)).getAdminId());
		}
		session.removeAttribute(SystemConstants.LOGINED);
		log_content="success:login out.";
		LogManageTools.writeAdminLog(log_content, adminLog);
		return "forward:login";
	}
}
