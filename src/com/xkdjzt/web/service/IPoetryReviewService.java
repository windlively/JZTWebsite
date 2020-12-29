package com.xkdjzt.web.service;

import java.util.Date;
import java.util.List;
import com.xkdjzt.web.entity.PoetryReview;

public interface IPoetryReviewService {
	public int addPoetryReview(PoetryReview poetryReview);
	public List<PoetryReview> getPoetryReviewListByPage(int page,int displayCount,String poetryType);
	public int getPoetryReviewCount();
	public int getMaxPage(int displayCount,String poetryType);
	public PoetryReview getPoetryReviewById(int reviewId);
	public int addReadAmounts(int reviewId);
	public int updatePoetryReview(PoetryReview poetryReview);
	public int deletePoetryReview(int reviewId);
	public List<PoetryReview> search(PoetryReview poetryReview,int page,int displayCount,Date startDate,Date endDate);
	public int getSearchedMaxPage(PoetryReview poetryReview,int displayCount,Date startDate,Date endDate);
}
