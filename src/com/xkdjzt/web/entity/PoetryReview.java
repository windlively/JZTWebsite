package com.xkdjzt.web.entity;

import java.io.Serializable;
import java.util.Date;

public class PoetryReview implements Serializable {
	private static final long serialVersionUID = -2595367322344234969L;
	private int reviewId; //ʫ���ı��
	private String poetryType; //ʫ������
	private String reviewTitle; //ʫ���ı���
	private String poetryTitle; //ʫ�ı���
	private String poetryAuthor; //ʫ������ 
	private String poetryAlign;//ʫ�İ�ʽ
	private String poetryContent; //ʫ������
	private String reviewAuthor; //ʫ��������
	private String reviewContent; //ʫ��������
	private int readAmounts; //�Ķ���
	private Date uploadTime; //�ϴ�ʱ��
	private Date updateTime; //�޸�ʱ��
	private String illuatrationPictureSrc;//��ͼ
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
