package com.bps.dto;

import java.io.Serializable;


/**
 * User points information
 *
 * @ClassName: Userinfo
 * @author: Phills
 * @date: 2014-8-21
 *
 */
public class TpointUser implements Serializable {
	
	private static final long serialVersionUID = 2031620924601998060L;
	
	private String userId;
	private int points;
	private boolean status;
			

	public TpointUser() {
		// TODO Auto-generated constructor stub
	}
	
	public TpointUser(String userId) {
		this.userId=userId;		
	}
	
	public TpointUser(String userId,int points) {
		this.userId=userId;
		this.points=points;		
	}
	
	public TpointUser(String userId,int points,boolean status) {
		this.userId=userId;
		this.points=points;
		this.status=status;
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

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
