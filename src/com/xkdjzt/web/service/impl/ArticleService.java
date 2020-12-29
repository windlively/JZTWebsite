package com.xkdjzt.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xkdjzt.web.dao.IArticleDao;
import com.xkdjzt.web.entity.Article;
import com.xkdjzt.web.service.IArticleService;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private IArticleDao articleDao;
	
	@Override
	public List<Article> getArticleListByPage(int page,int displayCount,String articleType) {
		List<Article> articleList = new ArrayList<>();
		Map<String,Object> searchPara = new HashMap<>();
		searchPara.put("startIndex", (page-1) * displayCount);
		searchPara.put("itemCount", displayCount);
		if("С˵".equals(articleType) || "ɢ��".equals(articleType)) {
			searchPara.put("articleType", articleType);
		}
		articleList = articleDao.search(searchPara);
		System.err.println("访问了数据库");
		return articleList;
	}
	
	@Override
	public int getMaxPage(int displayCount,String articleType) {
		Map<String,Object> searchPara = new HashMap<>();
		if("С˵".equals(articleType) || "ɢ��".equals(articleType)) {
			searchPara.put("articleType", articleType);
		}
		int articleCount = 0;
		articleCount =  articleDao.getSearchedCount(searchPara);
		return articleCount%displayCount == 0 ? articleCount/displayCount : articleCount/displayCount + 1;
	}
	
	@Transactional
	@Override
	public int addArticle(Article article) {
		if("".equals(article.getIlluatrationPictureSrc()) || null == article.getIlluatrationPictureSrc()) {
			article.setIlluatrationPictureSrc("");
		}
		return articleDao.addArticle(article);
	}
	
	@Override
	public Article getArticleById(int articleId) {
		return articleDao.getArticleById(articleId);
	}
	
	@Transactional
	@Override
	public int addReadAmounts(int articleId) {
		return articleDao.addReadAmounts(articleId);
	}
	
	@Transactional
	@Override
	public int updateArticle(Article article) {
		Article originArticle = articleDao.getArticleById(article.getArticleId());
		if(article.getArticleTitle() == null || article.getArticleTitle().equals("")) {
			article.setArticleTitle(originArticle.getArticleTitle());
		}
		if(article.getArticleAuthor() == null || article.getArticleAuthor().equals("")) {
			article.setArticleAuthor(originArticle.getArticleAuthor());
		}
		if(article.getArticleContent() == null || article.getArticleContent().equals("")) {
			article.setArticleContent(originArticle.getArticleContent());
		}
		if(article.getArticleType() == null || article.getArticleType().equals("")) {
			article.setArticleType(originArticle.getArticleType());
		}
		if(article.getIlluatrationPictureSrc() == null || article.getIlluatrationPictureSrc().equals("")) {
			article.setIlluatrationPictureSrc(originArticle.getIlluatrationPictureSrc());
		}
		return articleDao.updateArticle(article);
	}
	
	@Transactional
	@Override
	public int deleteArticleById(int articleId) {
		return articleDao.deleteArticleById(articleId);
	}
	
	@Override
	public List<Article> search(Article article,int page,int displayCount,Date startDate,Date endDate) {
		Map<String,Object> searchPara = new HashMap<>();
		searchPara.put("articleTitle", article.getArticleTitle());
		searchPara.put("articleAuthor", article.getArticleAuthor());
		searchPara.put("articleType", article.getArticleType());
		searchPara.put("startIndex", (page - 1) * displayCount);
		searchPara.put("itemCount", displayCount);
		searchPara.put("startDate", startDate);
		searchPara.put("endDate", endDate);
		return articleDao.search(searchPara);
	}
	@Override
	public int getSearchedMaxPage(Article article, int displayCount, Date startDate, Date endDate) {
		Map<String,Object> searchPara = new HashMap<>();
		searchPara.put("articleTitle", article.getArticleTitle());
		searchPara.put("articleAuthor", article.getArticleAuthor());
		searchPara.put("articleType", article.getArticleType());
		searchPara.put("startDate", startDate);
		searchPara.put("endDate", endDate);
		int articleCount = 0;
		articleCount = articleDao.getSearchedCount(searchPara);
		return articleCount%displayCount == 0 ? articleCount/displayCount : articleCount/displayCount + 1;
	}

}
