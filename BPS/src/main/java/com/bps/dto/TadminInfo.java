package com.bps.dto;

// Generated Oct 29, 2014 11:20:20 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * BpsAdminInfo generated by hbm2java
 */
public class TadminInfo implements java.io.Serializable {

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = -6411039121482151510L;
	private String adminId;
	private String firstName;
	private String lastName;
	private Boolean gender;
	private Date birthday;
	private String mobile;
	private String position;
	private String about;
	private byte[] avatarSmall;
	private byte[] avatar;

	public TadminInfo() {
	}

	public TadminInfo(String adminId) {
		this.adminId = adminId;
	}

	public TadminInfo(String adminId, String firstName, String lastName,
			Boolean gender, Date birthday, String mobile, String position,
			String about, byte[] avatarSmall, byte[] avatar) {
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.mobile = mobile;
		this.position = position;
		this.about = about;
		this.avatarSmall = avatarSmall;
		this.avatar = avatar;
	}

	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public byte[] getAvatarSmall() {
		return this.avatarSmall;
	}

	public void setAvatarSmall(byte[] avatarSmall) {
		this.avatarSmall = avatarSmall;
	}

	public byte[] getAvatar() {
		return this.avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

}
