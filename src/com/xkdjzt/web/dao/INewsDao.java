package com.xkdjzt.web.dao;

import java.util.List;
import java.util.Map;

import com.xkdjzt.web.entity.News;

public interface INewsDao {
	public int addNews(News news);
	public List<News> getNewsListByPage(Map<String,Integer> pageParameter);
	public News getNewsById(int newsId);
	public int getNewsCount();
	public List<News> search(Map<String,Object> searchPara);
	public int getSearchedCount(Map<String,Object> searchPara);
	public int addReadAmounts(int newsId);
	public int updateNews(News news);
	public int deleteNewsById(int newsId);
}
