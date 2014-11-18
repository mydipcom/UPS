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

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.bps.commons.SystemConfig;
import com.bps.dto.TadminNodes;
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
    			List<TadminNodes> nodesList=adminNodesService.getAllEnabledAdminNodes();
    			for (TadminNodes adminNodes : nodesList) {    				
    				SystemConfig.Admin_Nodes_Url_Map.put(adminNodes.getMethod()+"@"+adminNodes.getUri(), adminNodes.getBitFlag());
    				    
    				//Build menu mapping
    				if(adminNodes.isIsMenu()&&adminNodes.getPid()==0){
    					List<TadminNodes> menuList=adminNodesService.getAdminNodesMenuByPid(adminNodes.getNodeId());
    					SystemConfig.Admin_Nodes_Menu_Map.put(adminNodes, menuList);   	    					
    				}
				}
    		}
    	}
    	else{//projectName-servlet  context
    		
    	}
    }
}
