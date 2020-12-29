package com.xkdjzt.web.controller;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xkdjzt.web.entity.News;
import com.xkdjzt.web.service.INewsService;
import com.xkdjzt.web.util.MyTools;

@Controller
public class NewsController {
	@Autowired
	private INewsService newsService;
	
	@ResponseBody
	@RequestMapping("/getNewsList")
	public Map<String, Object> getNewsListByPage(HttpServletRequest request,
			@RequestParam(defaultValue = "1",required = false)int page,
			@RequestParam(defaultValue = "20",required = false)int displayCount,
			@RequestParam(defaultValue = "false",required = false)boolean useSessionPage) {
		Map<String,Object> result = new HashMap<>();
		if(useSessionPage == true && request.getSession().getAttribute("currentPage") != null) {
			page = (int) request.getSession().getAttribute("currentPage");
		}
		request.getSession().setAttribute("currentPage", page);
		result.put("newsList", newsService.getNewsListByPage(page, displayCount));
		result.put("maxPage", newsService.getSearchedMaxPage(null, displayCount, null, null));
		result.put("currentPage", page);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getNewsById")
	public Map<String,Object> getNewsById(int newsId,
			@RequestParam(defaultValue="true",required=false) boolean isRead) {
		Map<String,Object> result = new HashMap<>();
		result.put("news", newsService.getNewsById(newsId));
		if(isRead) {
			newsService.addReadAmounts(newsId);
		}
		return result;
	}

	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/deleteNewsById")
	public Map<String, Object> deleteNewsById(int newsId) {
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
		if(newsService.deleteNewsById(newsId) > 0) {
			isSuccess = true;
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/uploadNews")
	public Map<String,Object> uploadNews(News news,HttpServletRequest request) {		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("illuatrationPicture");
        Map<String,Object> result = new HashMap<>();
        boolean isSuccess = false;
        boolean isUpload = true;
        if(file != null && file.getSize() > 0) {
	        String fileOriginName = file.getOriginalFilename();
	        String fileType = file.getOriginalFilename().substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
	        String fileName = /*news.getNewsTitle() +*/ sdf.format(new Date()) + fileType;
			isUpload= MyTools.uploadFile(file, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/news/" + fileName);
			news.setIlluatrationPictureSrc("JZTResource/image/news/" + fileName);
        }
		if(isUpload) {
			news.setReadAmounts(0);
			news.setUploadTime(new Date());
			news.setUpdateTime(new Date());
			if(newsService.addNews(news) > 0) {
				isSuccess = true;
			}
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updateNewsFull")
	public Map<String,Object> updateNewsFull(News news,HttpServletRequest request) {
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	    MultipartFile file = multipartRequest.getFile("illuatrationPicture");   
	    boolean isUpload = true;
	    if(file != null && file.getSize() > 0) {
	        String fileOriginName = file.getOriginalFilename();
	        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm]");
	        String fileName = /*news.getNewsTitle() +*/ sdf.format(new Date()) + fileType;
			isUpload= MyTools.uploadFile(file, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/news/" + fileName);
			news.setIlluatrationPictureSrc("JZTResource/image/news/" + fileName);
	    }
		if(newsService.updateNews(news) > 0 && isUpload == true) {
			isSuccess = true;
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updateNewsWithoutFile")
	public Map<String,Object> updateNewsWithoutFile(News news) {
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
		if(newsService.updateNews(news) > 0) {
			isSuccess = true;
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/searchNews")
	public Map<String,Object> searchNews(HttpServletRequest request,News news,
			@RequestParam(defaultValue = "1",required = false)int page,
			@RequestParam(defaultValue = "20",required = false)int displayCount,
			@RequestParam(defaultValue="null",required=false)String startDate,
			@RequestParam(defaultValue="null",required=false)String endDate) {
		Map<String,Object> result = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sd = null;
		Date ed = null;
		try {
			if(!"null".equals(startDate)) {
				sd = sdf.parse(startDate);
			}
			if(!"null".equals(endDate)) {
				ed = sdf.parse(endDate);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		result.put("newsList", newsService.search(news, page, displayCount, sd, ed));
		result.put("maxPage", newsService.getSearchedMaxPage(news, displayCount, sd, ed));
		result.put("currentPage", page);
		return result;
	}
}
