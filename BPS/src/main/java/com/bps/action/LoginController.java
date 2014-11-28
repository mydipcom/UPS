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
		if(time != null && System.currentTimeMillis()-time<600000){
			    mav.addObject(ERROR_MSG_KEY, "账号被锁定10分钟");
				if(tUser != null){
				  mav.addObject("user", tUser);
				}else{
					mav.addObject("user", new TadminUser());
				}
				log_content=SystemConstants.LOG_FAILURE+":userid locked.";
		}
		else if(tUser==null){
			mav.addObject(ERROR_MSG_KEY, "用户名不存在");
			mav.addObject("user", new TadminUser());
			saveLoginErrorTims(request);
			log_content=SystemConstants.LOG_FAILURE+":userid error";
		}
		else if(!SecurityTools.SHA1(user.getPassword()).equalsIgnoreCase(tUser.getPassword())){
			mav.addObject(ERROR_MSG_KEY, "登录密码不正确");
			mav.addObject("user", tUser);
			saveLoginErrorTims(request);
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
			request.getSession().setAttribute(Lift_Flag, "Dashboard");
			mav.setViewName("redirect:"+toUrl);
			log_content=SystemConstants.LOG_SUCCESS+":login success.";
			
		}
		LogManageTools.writeLog(log_content,adminLog);
		return mav;
	}
	
	@RequestMapping(value="/ResetPassword",method=RequestMethod.POST)
	public ModelAndView resetPassword(HttpServletRequest request ,String email){
		ModelAndView mav = new ModelAndView();
		TadminUser adminUser = adminUserService.getTadminUsersByEmail(email);
		if(adminUser == null){
			 adminUser = new TadminUser();
			//邮箱不存在
		}else{
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
		}
		mav.addObject("user",adminUser);
		mav.setViewName("login");
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
		session.removeAttribute(SystemConstants.LOGINED);
		return "forward:login";
	}
}
