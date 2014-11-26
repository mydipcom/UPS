package com.bps.dto;

import java.util.Set;

public class TInterface implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Set<Param> param;
	
	private String descr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public Set<Param> getParam() {
		return param;
	}

	public void setParam(Set<Param> param) {
		this.param = param;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	

}
