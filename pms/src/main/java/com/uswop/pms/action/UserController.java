/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.uswop.pms.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	/**
	 * @Title: register
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/user/register")
	public String register() {		
		return "user/register";
	}

	/**
	 * @Title: login
	 * @Description: 找开登录页面请求
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
