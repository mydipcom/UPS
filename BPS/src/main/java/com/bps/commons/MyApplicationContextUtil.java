package com.bps.commons;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <p>获取系统Spring上下文对象的工具类</p>
 * @ClassName: MyApplicationContextUtil 
 * @author Phills Li 
 *
 */
public class MyApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;// 澹版槑涓�涓潤鎬佸彉閲忎繚瀛�

	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		MyApplicationContextUtil.context = contex;
	}

	public static ApplicationContext getContext() {
		return context;
	}

}
