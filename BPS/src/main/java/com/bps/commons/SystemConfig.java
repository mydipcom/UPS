package com.bps.commons;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.bps.dto.TadminNodes;

public class SystemConfig {
	
		
	public static String Api_Access_Key;
	
	public static Map<String,Long> Admin_Nodes_Url_Map=new Hashtable<String,Long>();
					
	public static Map<TadminNodes,List<TadminNodes>> Admin_Nodes_Menu_Map=new Hashtable<TadminNodes,List<TadminNodes>>();
	
	public static Map<String,List<TadminNodes>> Admin_Nodes_Group_Map=new Hashtable<String,List<TadminNodes>>();
	
	public static Map<String,String> Admin_Setting_Map=new Hashtable<String,String>();
		
}
