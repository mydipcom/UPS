/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.bps.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bps.dto.TadminUser;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	private Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView mav=new ModelAndView();
//		TadminUser tUser=new TadminUser();
//		mav.addObject("user", tUser);
		mav.setViewName("home/index");
		return mav;
	}

}
