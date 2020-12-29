package com.xkdjzt.web.service;

import java.util.Date;
import java.util.List;

import com.xkdjzt.web.entity.Article;

public interface IArticleService {
	public List<Article> getArticleListByPage(int page,int displayCount,String articleType);
	public int getMaxPage(int displayCount,String articleType);
	public int getSearchedMaxPage(Article article,int displayCount,Date startDate,Date endDate);
	public int addArticle(Article article);
	public Article getArticleById(int articleId);
	public int addReadAmounts(int readAmounts);
	public int updateArticle(Article article);
	public int deleteArticleById(int articleId);
	public List<Article> search(Article article,int page,int displayCount,Date startDate,Date endDate);
}
