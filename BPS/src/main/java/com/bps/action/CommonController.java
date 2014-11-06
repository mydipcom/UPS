/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.bps.action;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/common")
public class CommonController extends BaseController {

	private Logger logger = Logger.getLogger(CommonController.class);
	
	@RequestMapping(value="header",method=RequestMethod.GET)
	public ModelAndView header(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
//		TadminUser tUser=new TadminUser();
//		mav.addObject("user", tUser);
		mav.setViewName("common/header");
		return mav;
	}
	
	@RequestMapping(value="left",method=RequestMethod.GET)
	public ModelAndView left(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
//		TadminUser tUser=new TadminUser();
//		mav.addObject("user", tUser);
		mav.setViewName("common/left");
		return mav;
	}
	
	
	@RequestMapping(value="footer",method=RequestMethod.GET)
	public ModelAndView footer(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
//		TadminUser tUser=new TadminUser();
//		mav.addObject("user", tUser);
		mav.setViewName("common/footer");
		return mav;
	}
}
