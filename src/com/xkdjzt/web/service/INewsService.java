package com.xkdjzt.web.service;

import java.util.Date;
import java.util.List;

import com.xkdjzt.web.entity.News;

public interface INewsService {
	public int addNews(News news);
	public List<News> getNewsListByPage(int page,int displayCount);
	public News getNewsById(int newsId);
	public int getNewsCount();
	public int getMaxPage(int displayCount);
	public int addReadAmounts(int newsId);
	public int updateNews(News news);
	public int deleteNewsById(int newsId);
	public int getSearchedMaxPage(News news,int displayCount,Date startDate,Date endDate);
	public List<News> search(News news,int page,int displayCount,Date startDate,Date endDate);
}
