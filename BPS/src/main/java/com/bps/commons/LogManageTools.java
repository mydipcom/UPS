package com.bps.commons;
import org.springframework.stereotype.Controller;
import com.bps.commons.MyApplicationContextUtil;
import com.bps.commons.SystemConstants;
import com.bps.dto.TadminLog;
import com.bps.service.AdminuserLogService;


public class LogManageTools {
	private static long time;
	private static String clazz;
	private static String method;
	
	
	
	public static void writeLog(String content,Object obj){
		StackTraceElement[] ste = new Exception().getStackTrace();
	    clazz=ste[1].getClassName();
		method=ste[1].getMethodName();
		time=System.currentTimeMillis();
		if("com.bps.action.LoginController".equals(clazz)){
			AdminuserLogService adminuserLogService = (AdminuserLogService) MyApplicationContextUtil.getContext().getBean("adminuserLogService");
			TadminLog adminLog = (TadminLog) obj;
			adminLog.setCreatedTime(time);
			adminLog.setContent(clazz+SystemConstants.LOG_SEPARATOR+method+SystemConstants.LOG_SEPARATOR+content);
			adminuserLogService.createAdminLog(adminLog);
		}
		
	}

}
