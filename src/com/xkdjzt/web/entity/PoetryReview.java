package com.xkdjzt.web.entity;

import java.io.Serializable;
import java.util.Date;

public class PoetryReview implements Serializable {
	private static final long serialVersionUID = -2595367322344234969L;
	private int reviewId; //诗评的编号
	private String poetryType; //诗的类型
	private String reviewTitle; //诗评的标题
	private String poetryTitle; //诗的标题
	private String poetryAuthor; //诗的作者 
	private String poetryAlign;//诗的板式
	private String poetryContent; //诗的内容
	private String reviewAuthor; //诗评的作者
	private String reviewContent; //诗评的内容
	private int readAmounts; //阅读量
	private Date uploadTime; //上传时间
	private Date updateTime; //修改时间
	private String illuatrationPictureSrc;//插图
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getPoetryType() {
		return poetryType;
	}
	public void setPoetryType(String poetryType) {
		this.poetryType = poetryType;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getPoetryTitle() {
		return poetryTitle;
	}
	public void setPoetryTitle(String poetryTitle) {
		this.poetryTitle = poetryTitle;
	}
	public String getPoetryAuthor() {
		return poetryAuthor;
	}
	public void setPoetryAuthor(String poetryAuthor) {
		this.poetryAuthor = poetryAuthor;
	}
	public String getPoetryContent() {
		return poetryContent;
	}
	public void setPoetryContent(String poetryContent) {
		this.poetryContent = poetryContent;
	}
	public String getReviewAuthor() {
		return reviewAuthor;
	}
	public void setReviewAuthor(String reviewAuthor) {
		this.reviewAuthor = reviewAuthor;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
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
	public String getIlluatrationPictureSrc() {
		return illuatrationPictureSrc;
	}
	public void setIlluatrationPictureSrc(String illuatrationPictureSrc) {
		this.illuatrationPictureSrc = illuatrationPictureSrc;
	}
	public String getPoetryAlign() {
		return poetryAlign;
	}
	public void setPoetryAlign(String poetryAlign) {
		this.poetryAlign = poetryAlign;
	}
	@Override
	public String toString() {
		return "PoetryReview [reviewId=" + reviewId + ", poetryType=" + poetryType + ", reviewTitle=" + reviewTitle
				+ ", poetryTitle=" + poetryTitle + ", poetryAuthor=" + poetryAuthor + ", poetryAlign=" + poetryAlign
				+ ", poetryContent=" + poetryContent + ", reviewAuthor=" + reviewAuthor + ", reviewContent="
				+ reviewContent + ", readAmounts=" + readAmounts + ", uploadTime=" + uploadTime + ", updateTime="
				+ updateTime + ", illuatrationPictureSrc=" + illuatrationPictureSrc + "]";
	}
	
	
}
