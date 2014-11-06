package com.bps.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the admin_user database table.
 * 
 */
public class TpointsLog implements Serializable {
	
	
	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 3762424140634616981L;

	private int id;

	private TpointUser pointUser;

	private int points;

	private int pointsBalance;

	private String content;	

	private boolean status;
	
	private int from;

	private long createdTime;
		
	private String createdTimeStr;	

	public TpointsLog() {
	}
	
	public TpointsLog(TpointUser pointUser,int points,int pointsBalance,String content,boolean status,int from, long createdTime) {
		this.pointUser=pointUser;
		this.points=points;
		this.pointsBalance=pointsBalance;
		this.setContent(content);
		this.createdTime=createdTime;
		this.status=status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPointsBalance() {
		return pointsBalance;
	}

	public void setPointsBalance(int pointsBalance) {
		this.pointsBalance = pointsBalance;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedTimeStr() {
		Date date=new Date(createdTime);
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return sdf.format(date);		
	}

	public void setCreatedTimeStr(String createdTimeStr) {		
		this.createdTimeStr = createdTimeStr;
	}

	/**
	 * @return the pointUser
	 */
	public TpointUser getPointUser() {
		return pointUser;
	}

	/**
	 * @param pointUser the pointUser to set
	 */
	public void setPointUser(TpointUser pointUser) {
		this.pointUser = pointUser;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}

}