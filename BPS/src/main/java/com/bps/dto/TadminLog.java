package com.bps.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

// Generated Oct 29, 2014 11:20:20 AM by Hibernate Tools 3.4.0.CR1

/**
 * BpsAdminLog generated by hbm2java
 */
public class TadminLog implements java.io.Serializable {

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 7135417645070911481L;
	private Integer id;
	private String adminId;
	private String content;
	private short level;
	private Long createdTime;
	
	private String createdTimeStr;

	public TadminLog() {
	}

	public TadminLog(String adminId, String content, short level,
			long createdTime) {
		this.adminId = adminId;
		this.content = content;
		this.level = level;
		this.createdTime = createdTime;
	}

	
	
	
	
	public String getCreatedTimeStr() {
		if(createdTime!=null){
			Date date=new Date(createdTime);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			return sdf.format(date);		
			}else
			return null;
	
	}

	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getLevel() {
		return this.level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public long getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

}
