/**   
 * @Title: CachedDataProcessor.java 
 * @Package com.bps.core 
 *
 * @Description: User Points Management System
 * 
 * @date Nov 13, 2014 5:23:42 PM
 * @version V1.0   
 */ 
package com.bps.core;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.bps.service.AdminNodesService;

/** 
 * <p>Sping容器初始化完成后的缓存甚而数据的处理方法</p>
 * @ClassName: CachedDataProcessor 
 * @author Phills Li 
 * 
 */
public class CachedDataProcessor implements ApplicationListener<ContextRefreshedEvent> {
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
		//root application context
    	if(event.getApplicationContext().getParent() == null){
    		AdminNodesService adminNodesService=(AdminNodesService) event.getApplicationContext().getBean("adminNodesService");
    		if(adminNodesService!=null){
    			adminNodesService.cachedNodesData();    			
    		}
    	}
    	else{//projectName-servlet  context
    		
    	}
    }
}
