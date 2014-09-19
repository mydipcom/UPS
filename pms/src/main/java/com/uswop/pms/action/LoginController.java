/**   
* @Title: LoginController.java 
* @Package com.uswop.action 
*
* @Description: TODO
* 
* @date Sep 10, 2014 3:06:32 PM
* @version V1.0   
*/ 
package com.uswop.pms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uswop.pms.commons.SystemConstants;
import com.uswop.pms.dto.TadminUser;
import com.uswop.pms.service.AdminUserService;

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
	
	/** 
	 * <p>Open the login page</p>
	 * @Title: login 
	 * @return String
	 * @throws 
	 */ 
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
		TadminUser tUser=(TadminUser)adminUserService.getAdminUserById(user.getUserId());
		ModelAndView mav=new ModelAndView();
		if(tUser==null){
			mav.addObject(ERROR_MSG_KEY, "用户名不存在");
		}
		else{
			setSessionUser(request, tUser);
			String toUrl=(String)request.getSession().getAttribute(LOGIN_TO_URL);
			request.getSession().removeAttribute(LOGIN_TO_URL);
			if(StringUtils.isEmpty(toUrl)){
				toUrl="home";
			}
			mav.setViewName("redirect:"+toUrl);
		}
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
