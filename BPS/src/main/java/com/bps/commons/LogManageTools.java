package com.bps.commons;
import com.bps.commons.MyApplicationContextUtil;
import com.bps.commons.SystemConstants;
import com.bps.dto.TInterfaceLog;
import com.bps.dto.TadminLog;
import com.bps.dto.TpointRuleLog;
import com.bps.dto.TpointsLog;
import com.bps.service.AdminuserLogService;
import com.bps.service.InterfaceLogService;
import com.bps.service.PointsLogService;
import com.bps.service.RulesLogService;


public class LogManageTools {
	private static long time;
	private static String clazz;
	private static String method;
	
	
	
	public static void writeAdminLog(String content,Object obj){
		StackTraceElement[] ste = new Exception().getStackTrace();
		AdminuserLogService adminuserLogService = (AdminuserLogService) MyApplicationContextUtil.getContext().getBean("adminuserLogService");
	    clazz=ste[1].getClassName();
		method=ste[1].getMethodName();
		time=System.currentTimeMillis();
	    TadminLog adminLog = (TadminLog) obj;
		adminLog.setCreatedTime(time);
	    adminLog.setContent(clazz+SystemConstants.LOG_SEPARATOR+method+SystemConstants.LOG_SEPARATOR+content);
	    adminuserLogService.createAdminLog(adminLog);
	}
	
	public static void writePointLog(String content,Object obj){
		time=System.currentTimeMillis();
		StackTraceElement[] ste = new Exception().getStackTrace();
		TpointsLog pointLog=new TpointsLog();
		clazz=ste[1].getClassName();
		method=ste[1].getMethodName();
		PointsLogService pointsLogService=(PointsLogService)MyApplicationContextUtil.getContext().getBean("pointsLogService");
		pointLog =(TpointsLog)obj;
		pointLog.setCreatedTime(time);
		pointLog.setContent(clazz+SystemConstants.LOG_SEPARATOR+method+SystemConstants.LOG_SEPARATOR+content);
		pointsLogService.createPointsHistory(pointLog);
	}
	
	public static void writePointRuleLog(String content,Object obj){
		
		StackTraceElement[] ste = new Exception().getStackTrace();
		clazz=ste[1].getClassName();
		method=ste[1].getMethodName();
		time=System.currentTimeMillis();
		RulesLogService rulesLogService = (RulesLogService) MyApplicationContextUtil.getContext().getBean("rulesLogService");
		TpointRuleLog pointRuleLog =(TpointRuleLog) obj;
		pointRuleLog.setCreatedTime(time);
		pointRuleLog.setContent(clazz+SystemConstants.LOG_SEPARATOR+method+SystemConstants.LOG_SEPARATOR+content);
		rulesLogService.createRuleLog(pointRuleLog);
	}
	
	public static void writeInterfaceLog(String content,Object obj){
		StackTraceElement[] ste = new Exception().getStackTrace();
		clazz=ste[1].getClassName();
		method=ste[1].getMethodName();
		time=System.currentTimeMillis();
		InterfaceLogService interfaceLogService=(InterfaceLogService)MyApplicationContextUtil.getContext().getBean("interfaceLogService");
		TInterfaceLog interfaceLog = (TInterfaceLog)obj;
		interfaceLog.setAccessTime(time);
		interfaceLog.setContent(clazz+SystemConstants.LOG_SEPARATOR+method+SystemConstants.LOG_SEPARATOR+content);
		interfaceLogService.createInterfaceLog(interfaceLog);
		
	}

}
