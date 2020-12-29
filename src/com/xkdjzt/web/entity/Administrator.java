package com.xkdjzt.web.entity;

import java.io.Serializable;

public class Administrator implements Serializable {
	private static final long serialVersionUID = -1903440260933765595L;
	private String adminName;
	private String adminPassword;
	private String permit;
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
