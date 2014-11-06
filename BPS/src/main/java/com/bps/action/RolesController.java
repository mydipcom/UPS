/**   
 * @Title: RightsController.java 
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bps.dto.TadminNodes;

/**
 * @ClassName: RightsController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
public class RolesController extends BaseController {

	private Logger logger = Logger.getLogger(RolesController.class);

	@RequestMapping(value="/roles",method=RequestMethod.GET)
	public ModelAndView rolesList(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("roles/roles");
		return mav;
	}
	
	@RequestMapping(value="/roles/{id}",method=RequestMethod.GET)
	public ModelAndView rights(@PathVariable int id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("roles/roles");
		return mav;
	}
	
	@RequestMapping(value="/roles",method=RequestMethod.POST)
	public ModelAndView addRights(HttpServletRequest request,TadminNodes adminNodes){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("roles/roles");
		return mav;
	}
	
	@RequestMapping(value="/roles/{id}",method=RequestMethod.PUT)
	public ModelAndView updateRights(HttpServletRequest request,TadminNodes adminNodes){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("roles/roles");
		return mav;
	}

	@RequestMapping(value="/roles/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteRights(@PathVariable int id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();

//		mav.addObject("user", tUser);
		mav.setViewName("roles/roles");
		return mav;
	}
}
