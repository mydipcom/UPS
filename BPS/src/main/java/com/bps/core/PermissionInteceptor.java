package com.bps.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bps.commons.SystemConstants;

public class PermissionInteceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String reqMethod=request.getMethod();
		String uri=request.getRequestURI();
		String contextPath=request.getContextPath();
		if((contextPath+"/login").equalsIgnoreCase(uri)){
			return true;
		}
		// TODO Auto-generated method stub
		Object obj = request.getSession().getAttribute(SystemConstants.LOGINED);
		//If not login
		if (null == obj) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return true;
	}

}
