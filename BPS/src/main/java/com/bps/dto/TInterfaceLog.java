package com.bps.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TInterfaceLog implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  int id;
	
	private String name;
	
	private Long  accessTime;
	
	private String accessBy;
	
	private String accessTimestr;
	
	private String content;

	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Long accessTime) {
		this.accessTime = accessTime;
	}

	public String getAccessBy() {
		return accessBy;
	}

	public void setAccessBy(String accessBy) {
		this.accessBy = accessBy;
	}

	public String getAccessTimestr() {
		if(accessTime!=null){
			Date date=new Date(accessTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
			}else
			return this.accessTimestr;
	}

	public void setAccessTimestr(String accessTimestr) {
			this.accessTimestr = accessTimestr;
	}
	
	

}
