/**   
* @Title: BaseController.java 
* @Package com.uswop.action 
*
* @Description: 积分管理系统
* 
* @date Sep 10, 2014 3:27:05 PM
* @version V1.0   
*/ 
package com.uswop.pms.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.uswop.pms.commons.SystemConstants;
import com.uswop.pms.dto.TadminUser;


/** 
 * <p>Spring控制器的基类</p>
 * <此类实现了一些控制器处理类通用的方法，实际的业务控制器可以根据需要继承此类>
 * @ClassName: BaseController 
 * @author Phills Li 
 *  
 */ 
public class BaseController {
	
	protected final String ERROR_MSG_KEY="errorMsg";
	protected final String LOGIN_TO_URL="LoginToUrl";
	
	/** 
	 * <p>获取保存在Session中的用户对象 </p>
	 * @Title: getSessionUser 
	 * @param request
	 * @return User
	 * @throws 
	 */ 
	protected TadminUser getSessionUser(HttpServletRequest request){
		return (TadminUser)request.getSession().getAttribute(SystemConstants.LOGINED);
	}
		
	/** 
	 * <p>保存登录用户对象到Session中</p>
	 * @Title: setSessionUser 
	 * @param request
	 * @param user
	 * @throws 
	 */ 
	protected void setSessionUser(HttpServletRequest request,TadminUser user){
		request.getSession().setAttribute(SystemConstants.LOGINED, user);
	}
		
	/** 
	 * <p>获取基于应用程序的url的绝对路径</p>
	 * @Title: getAppBaseUrl 
	 * @param request
	 * @param url
	 * @return String
	 * @throws 
	 */ 
	public final String getAppBaseUrl(HttpServletRequest request,String url){
		Assert.hasLength(url,"url不能为空");
		Assert.isTrue(url.startsWith("/"),"必须以/开头");
		return request.getContextPath()+url;
	}
}
