package com.xkdjzt.web.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
	private static final long serialVersionUID = 606925841029193892L;
	private int articleId;
	private String articleType;
	private String articleTitle;
	private String articleAuthor;
	private String articleContent;
	private String illuatrationPictureSrc;
	private int readAmounts;
	private Date uploadTime;
	private Date updateTime;
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleAuthor() {
		return articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
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
		return "Article [articleId=" + articleId + ", articleType=" + articleType + ", articleTitle=" + articleTitle
				+ ", articleAuthor=" + articleAuthor + ", articleContent=" + articleContent
				+ ", illuatrationPictureSrc=" + illuatrationPictureSrc + ", readAmounts=" + readAmounts
				+ ", uploadTime=" + uploadTime + ", updateTime=" + updateTime + "]";
	}
	
}
