/**   
 * @Title: RegisterController.java 
 * @Package com.uswop.action 
 *
 * @Description: User Points Management System
 * 
 * @date Sep 10, 2014 4:29:20 PM
 * @version V1.0   
 */ 
package com.uswop.pms.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uswop.pms.dto.TadminRole;
import com.uswop.pms.dto.TadminUser;
import com.uswop.pms.service.AdminUserService;

/** 
 * <p>A controller for handle the user register request</p>
 * @ClassName: RegisterController 
 * @author Phills Li 
 * 
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
	@Autowired
	private AdminUserService adminUserService;
	
	/** 
	 * <p>Open the register page</p>
	 * @Title: register 
	 * @return String
	 * @throws 
	 */ 
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView mav=new ModelAndView();
		TadminUser tUser=new TadminUser();
		mav.addObject("user", tUser);
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,TadminUser user){		
		ModelAndView mav=new ModelAndView();
		try{
			user.setStatus(true);
			TadminRole adminRole=new TadminRole();
			adminRole.setRoleId(1);
			user.setAdminRole(adminRole);
			user.setUpdateTime(System.currentTimeMillis());
			adminUserService.createAdminUser(user);
			setSessionUser(request, user);			
			mav.setViewName("home");
		}
		catch(Exception e){
			mav.addObject(ERROR_MSG_KEY,e.getMessage());
			mav.setViewName("register");
		}								
		return mav;
	}
}
