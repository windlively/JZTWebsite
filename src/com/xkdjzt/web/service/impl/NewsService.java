package com.xkdjzt.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xkdjzt.web.dao.INewsDao;
import com.xkdjzt.web.entity.News;
import com.xkdjzt.web.service.INewsService;

@Service
public class NewsService implements INewsService {
	@Autowired
	private INewsDao newsDao;
	
	@Transactional
	@Override
	public int addNews(News news) {
		if("".equals(news.getIlluatrationPictureSrc())) {
			news.setIlluatrationPictureSrc("");
		}
		return newsDao.addNews(news);
	}
	@Override
	public List<News> getNewsListByPage(int page, int displayCount) {
		List<News> newsList = new ArrayList<>();
		Map<String,Integer> pageParameter = new HashMap<>();
		pageParameter.put("startIndex", (page-1) * displayCount);
		pageParameter.put("itemCount", displayCount);
		newsList = newsDao.getNewsListByPage(pageParameter);
		return newsList;
	}
	@Override
	public News getNewsById(int newsId) {
		return newsDao.getNewsById(newsId);
	}
	@Override
	public int getNewsCount() {
		return newsDao.getNewsCount();
	}
	@Override
	public int getMaxPage(int displayCount) {
		int newsCount = this.getNewsCount();
		return newsCount%displayCount == 0 ? newsCount/displayCount : newsCount/displayCount + 1;
	}
	@Transactional
	@Override
	public int addReadAmounts(int newsId) {
		return newsDao.addReadAmounts(newsId);
	}
	
	@Transactional
	@Override
	public int updateNews(News news) {
		News originNews = newsDao.getNewsById(news.getNewsId());
		if(news.getNewsTitle() == null || news.getNewsTitle().equals("")) {
			news.setNewsTitle(originNews.getNewsTitle());
		}
		if(news.getNewsAuthor() == null || news.getNewsAuthor().equals("")) {
			news.setNewsAuthor(originNews.getNewsAuthor());
		}
		if(news.getNewsContent() == null || news.getNewsContent().equals("")) {
			news.setNewsContent(originNews.getNewsContent());
		}
		if(news.getNewsInfo() == null || news.getNewsInfo().equals("")) {
			news.setNewsInfo(originNews.getNewsInfo());
		}
		if(news.getIlluatrationPictureSrc() == null || news.getIlluatrationPictureSrc().equals("")) {
			news.setIlluatrationPictureSrc(originNews.getIlluatrationPictureSrc());
		}
		return newsDao.updateNews(news);
	}
	@Transactional
	@Override
	public int deleteNewsById(int newsId) {
		return newsDao.deleteNewsById(newsId);
	}
	@Override
	public int getSearchedMaxPage(News news, int displayCount, Date startDate, Date endDate) {
		Map<String,Object> searchPara = new HashMap<>();
		if(news != null) {
			searchPara.put("newsTitle", news.getNewsTitle());
			searchPara.put("newsAuthor", news.getNewsAuthor());
		}
		searchPara.put("startDate", startDate);
		searchPara.put("endDate", endDate);
		int newsCount = 0;
		newsCount = newsDao.getSearchedCount(searchPara);
		return newsCount%displayCount == 0 ? newsCount/displayCount : newsCount/displayCount + 1;
	}
	@Override
	public List<News> search(News news, int page, int displayCount, Date startDate, Date endDate) {
		List<News> newsList = new ArrayList<>();
		Map<String,Object> searchPara= new HashMap<>();
		if(news != null) {
			searchPara.put("newsTitle", news.getNewsTitle());
			searchPara.put("newsAuthor", news.getNewsAuthor());
		}
		searchPara.put("startDate", startDate);
		searchPara.put("endDate", endDate);
		searchPara.put("startIndex", (page - 1) * displayCount);
		searchPara.put("itemCount", displayCount);
		newsList = newsDao.search(searchPara);
		return newsList;
	}
	
}
