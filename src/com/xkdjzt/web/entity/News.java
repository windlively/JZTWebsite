package com.xkdjzt.web.entity;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable{
	private static final long serialVersionUID = -5045357546507697648L;
	private int newsId;
	private String newsTitle;
	private String newsInfo;
	private String newsAuthor;
	private String newsContent;
	private String illuatrationPictureSrc;
	private int readAmounts;
	private Date uploadTime;
	private Date updateTime;
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsInfo() {
		return newsInfo;
	}
	public void setNewsInfo(String newsInfo) {
		this.newsInfo = newsInfo;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getIlluatrationPictureSrc() {
		return illuatrationPictureSrc;
	}
	public void setIlluatrationPictureSrc(String illuatrationPictureSrc) {
		this.illuatrationPictureSrc = illuatrationPictureSrc;
	}
	public int getReadAmounts() {
		return readAmounts;
	}
	public void setReadAmounts(int readAmounts) {
		this.readAmounts = readAmounts;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsTitle=" + newsTitle + ", newsInfo=" + newsInfo + ", newsAuthor="
				+ newsAuthor + ", newsContent=" + newsContent + ", illuatrationPictureSrc=" + illuatrationPictureSrc
				+ ", readAmounts=" + readAmounts + ", uploadTime=" + uploadTime + ", updateTime=" + updateTime + "]";
	}
}
