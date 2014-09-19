package com.uswop.pms.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the admin_user database table.
 * 
 */
public class TpointsHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;

	private String userId;

	private int points;

	private int pointsBalance;

	private String message;	

	private boolean status;

	private long createdTime;
	
	private String createdTimeStr;	

	public TpointsHistory() {
	}
	
	public TpointsHistory(String userId,int points,int pointsBalance,String message,boolean status,long createdTime) {
		this.userId=userId;
		this.points=points;
		this.pointsBalance=pointsBalance;
		this.message=message;
		this.createdTime=createdTime;
		this.status=status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}