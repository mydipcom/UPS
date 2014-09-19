package com.uswop.pms.dto;

import java.io.Serializable;


/**
 * The persistent class for the admin_user database table.
 * 
 */
public class TadminUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;

	private String createdBy;

	private String email;

	private String mobilePhone;

	private String password;

	private String realName;

	private boolean status;

	private String updateBy;
	
	private long updateTime;
	
	private TadminRole adminRole;

	public TadminUser() {
	}
	
	public TadminUser(String userId,String password,TadminRole adminRole,boolean status) {
		this.userId=userId;
		this.password=password;
		this.adminRole=adminRole;
		this.status=status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public long getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public TadminRole getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(TadminRole adminRole) {
		this.adminRole = adminRole;
	}

}