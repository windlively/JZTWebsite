package com.xkdjzt.web.dao;

import java.util.List;
import java.util.Map;

import com.xkdjzt.web.entity.PoetryReview;

public interface IPoetryReviewDao {
	public int addPoetryReview(PoetryReview poetryReview);
	public List<PoetryReview> getPoetryReviewListByPage(Map<String, Integer> pageParameter);
	public List<PoetryReview> search(Map<String,Object> searchPara);
	public int getSearchedCount(Map<String,Object> searchPara);
	public int getPoetryReviewCount();
	public PoetryReview getPoetryReviewById(int reviewId);
	public int addReadAmounts(int reviewId);
	public int updatePoetryReview(PoetryReview poetryReview);
	public int deletePoetryReview(int reviewId);
}
