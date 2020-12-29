package com.xkdjzt.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xkdjzt.web.dao.IPoetryReviewDao;
import com.xkdjzt.web.entity.PoetryReview;
import com.xkdjzt.web.service.IPoetryReviewService;

@Service
public class PoetryReviewService implements IPoetryReviewService {
	@Autowired
	private IPoetryReviewDao poetryReviewDao;
	
	@Transactional
	@Override
	public int addPoetryReview(PoetryReview poetryReview) {
		if("".equals(poetryReview.getIlluatrationPictureSrc())) {
			poetryReview.setIlluatrationPictureSrc("");
		}
		return poetryReviewDao.addPoetryReview(poetryReview);
	}
	@Override
	public List<PoetryReview> getPoetryReviewListByPage(int page, int displayCount, String poetryType) {
		List<PoetryReview> poetryReviewList = new ArrayList<>();
		Map<String,Object> searchPara = new HashMap<>();
		searchPara.put("startIndex", (page-1) * displayCount);
		searchPara.put("itemCount", displayCount);
		if("����ʫ".equals(poetryType) || "���ִ�ʫ".equals(poetryType) || "ԭ��ʫ".equals(poetryType)) {
			searchPara.put("poetryType", poetryType);
		}
		poetryReviewList = poetryReviewDao.search(searchPara);
		return poetryReviewList;
	}
	@Override
	public int getPoetryReviewCount() {
		return poetryReviewDao.getPoetryReviewCount();
	}
	@Override
	public int getMaxPage(int displayCount,String poetryType) {
		Map<String,Object> searchPara = new HashMap<>();
		if("����ʫ".equals(poetryType) || "���ִ�ʫ".equals(poetryType) || "ԭ��ʫ".equals(poetryType)) {
			searchPara.put("poetryType", poetryType);
		}
		int poetryReviewCount = 0;		
		poetryReviewCount = poetryReviewDao.getSearchedCount(searchPara);
		return poetryReviewCount%displayCount == 0 ? poetryReviewCount/displayCount : poetryReviewCount/displayCount + 1;
	}
	@Override
	public PoetryReview getPoetryReviewById(int reviewId) {
		return poetryReviewDao.getPoetryReviewById(reviewId);
	}
	
	@Transactional
	@Override
	public int addReadAmounts(int reviewId) {
		return poetryReviewDao.addReadAmounts(reviewId);
	}
	
	@Transactional
	@Override
	public int updatePoetryReview(PoetryReview poetryReview) {
		PoetryReview originPoetryReview = poetryReviewDao.getPoetryReviewById(poetryReview.getReviewId());
		
		if("".equals(poetryReview.getPoetryType()) || poetryReview.getPoetryType() == null) {
			poetryReview.setPoetryType(originPoetryReview.getPoetryType());
		}
		if("".equals(poetryReview.getReviewTitle()) || poetryReview.getReviewTitle() == null){
			poetryReview.setReviewTitle(originPoetryReview.getReviewTitle());
		}
		if("".equals(poetryReview.getPoetryTitle()) || poetryReview.getPoetryTitle() == null) {
			poetryReview.setPoetryTitle(originPoetryReview.getPoetryTitle());
		}
		if("".equals(poetryReview.getPoetryAuthor()) || poetryReview.getPoetryAuthor() == null) {
			poetryReview.setPoetryAuthor(originPoetryReview.getPoetryAuthor());
		}
		if("".equals(poetryReview.getPoetryContent()) || poetryReview.getPoetryContent() == null) {
			poetryReview.setPoetryContent(originPoetryReview.getPoetryContent());
		}
		if("".equals(poetryReview.getReviewAuthor()) || poetryReview.getReviewAuthor() == null) {
			poetryReview.setReviewAuthor(originPoetryReview.getReviewAuthor());
		}
		if("".equals(poetryReview.getReviewContent()) || poetryReview.getReviewContent() == null) {
			poetryReview.setReviewContent(originPoetryReview.getReviewContent());
		}
		if("".equals(poetryReview.getIlluatrationPictureSrc()) || poetryReview.getIlluatrationPictureSrc() == null) {
			poetryReview.setIlluatrationPictureSrc(originPoetryReview.getIlluatrationPictureSrc());
		}
		return poetryReviewDao.updatePoetryReview(poetryReview);
	}
	
	@Transactional
	@Override
	public int deletePoetryReview(int reviewId) {
		return poetryReviewDao.deletePoetryReview(reviewId);
	}
	@Override
	public List<PoetryReview> search(PoetryReview poetryReview, int page, int displayCount, Date startDate,
			Date endDate) {
		List<PoetryReview> poetryReviewList = new ArrayList<>();
		Map<String,Object> searchPara = new HashMap<>();
		if(poetryReview != null) {
			searchPara.put("reviewTitle", poetryReview.getReviewTitle());
			searchPara.put("poetryTitle", poetryReview.getPoetryTitle());
			searchPara.put("poetryAuthor", poetryReview.getPoetryAuthor());
			searchPara.put("reviewAuthor", poetryReview.getReviewAuthor());
			searchPara.put("poetryType", poetryReview.getPoetryType());
		}
		searchPara.put("startIndex", (page - 1) * displayCount);
		searchPara.put("itemCount", displayCount);
		searchPara.put("startDate", startDate);
		searchPara.put("endDate", endDate);
		poetryReviewList = poetryReviewDao.search(searchPara);
		return poetryReviewList;
	}
	@Override
	public int getSearchedMaxPage(PoetryReview poetryReview, int displayCount, Date startDate, Date endDate) {
		Map<String,Object> searchPara = new HashMap<>();
		if(poetryReview != null) {
			searchPara.put("reviewTitle", poetryReview.getReviewTitle());
			searchPara.put("poetryTitle", poetryReview.getPoetryTitle());
			searchPara.put("poetryAuthor", poetryReview.getPoetryAuthor());
			searchPara.put("reviewAuthor", poetryReview.getReviewAuthor());
			searchPara.put("poetryType", poetryReview.getPoetryType());
		}
		searchPara.put("startDate", startDate);
		searchPara.put("endDate", endDate);
		int poetryReviewCount = 0;
		poetryReviewCount = poetryReviewDao.getSearchedCount(searchPara);
		return poetryReviewCount%displayCount == 0 ? poetryReviewCount/displayCount : poetryReviewCount/displayCount + 1;
	}

}
