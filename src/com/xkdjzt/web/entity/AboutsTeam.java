package com.xkdjzt.web.entity;

import java.io.Serializable;
import java.util.Date;

public class AboutsTeam implements Serializable{
	private static final long serialVersionUID = -5405450201355108776L;
	private int id;
	private String content;
	private Date updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "AboutsTeam [id=" + id + ", content=" + content + ", updateTime=" + updateTime + "]";
	}
	
}
