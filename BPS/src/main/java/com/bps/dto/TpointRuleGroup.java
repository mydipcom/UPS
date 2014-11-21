package com.bps.dto;

import java.io.Serializable;

public class TpointRuleGroup  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int groupId;
	
	private String groupName;
	
	private String descr;
	


	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	

	
}
