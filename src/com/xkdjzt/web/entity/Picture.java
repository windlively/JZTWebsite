package com.xkdjzt.web.entity;

import java.io.Serializable;

public class Picture implements Serializable {
	private static final long serialVersionUID = -2454386872710188634L;
	private int pictureId;
	private String pictureType;
	private String pictureName;
	private String pictureInfo;
	private String pictureSrc;
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public String getPictureType() {
		return pictureType;
	}
	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getPictureInfo() {
		return pictureInfo;
	}
	public void setPictureInfo(String pictureInfo) {
		this.pictureInfo = pictureInfo;
	}
	public String getPictureSrc() {
		return pictureSrc;
	}
	public void setPictureSrc(String pictureSrc) {
		this.pictureSrc = pictureSrc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Picture [pictureId=" + pictureId + ", pictureType=" + pictureType + ", pictureName=" + pictureName
				+ ", pictureInfo=" + pictureInfo + ", pictureSrc=" + pictureSrc + "]";
	}
	
}
