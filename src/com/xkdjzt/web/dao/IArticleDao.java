package com.xkdjzt.web.dao;

import java.util.List;
import java.util.Map;

import com.xkdjzt.web.entity.Article;

public interface IArticleDao {
	public int addArticle(Article article);
	public List<Article> getArticleListByPage(Map<String, Integer> pageParameter);
	public int getArticleCount();
	public Article getArticleById(int articleId);
	public int addReadAmounts(int articleId);
	public int updateArticle(Article article);
	public int deleteArticleById(int articleId);
	public List<Article> search(Map<String, Object> searchPara);
	public int getSearchedCount(Map<String, Object> searchPara);
	public int getMaxId();
}
