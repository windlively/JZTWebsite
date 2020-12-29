package com.xkdjzt.web.controller;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.xkdjzt.web.entity.Article;
import com.xkdjzt.web.service.IArticleService;
import com.xkdjzt.web.util.MyTools;

@Controller
public class ArticleController {
	@Autowired 
	private IArticleService articleService;
	
	@ResponseBody
	@RequestMapping("/getArticleList")
	public Map<String,Object> getArticleListByPage(HttpServletRequest request,
			@RequestParam(defaultValue = "1",required = false)int page,
			@RequestParam(defaultValue = "20",required = false)int displayCount,
			@RequestParam(defaultValue = "all",required = false, value="type")String articleType,
			@RequestParam(defaultValue = "false",required = false)boolean useSessionPage) {
		Map<String,Object> result = new HashMap<>();
		if(useSessionPage == true && request.getSession().getAttribute("currentPage") != null) {
			page = (int) request.getSession().getAttribute("currentPage");
		}
		request.getSession().setAttribute("currentPage", page);
		List<Article> articleList = null;
		Article article = new Article();
		if("小说".equals(articleType) || "散文".equals(articleType)) {
			article.setArticleType(articleType);
			articleList = articleService.search(article, page, displayCount, null, null);
			result.put("maxPage", articleService.getSearchedMaxPage(article, displayCount, null, null));
		}else {
			articleList = articleService.getArticleListByPage(page, displayCount, null);
			result.put("maxPage", articleService.getMaxPage(displayCount,articleType));
		}
		result.put("articleList", articleList);
		result.put("currentPage", page);
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/uploadArticle")
	public Map<String,Object> uploadArticle(Article article,HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
        MultipartFile file = multipartRequest.getFile("illuatrationPicture"); 
        boolean isUpload = true;
        //如果含有文件
        if(file != null && file.getSize() > 0) {
	        String fileOriginName = file.getOriginalFilename();
	        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
	        String fileName =/* article.getArticleTitle() + */sdf.format(new Date()) + fileType;
			isUpload= MyTools.uploadFile(file, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/article/" + fileName);
			article.setIlluatrationPictureSrc("JZTResource/image/article/" + fileName);
        }
		if(isUpload) {
			article.setReadAmounts(0);
			article.setUploadTime(new Date());
			article.setUpdateTime(new Date());
//			result.put("isSuccess", articleService.addArticle(article));
			if(articleService.addArticle(article) > 0) {
				isSuccess = true;
			}
		}
		result.put("isSccess", isSuccess ? 1 : 0);
		return result;
	}

	
	@ResponseBody
	@RequestMapping("/getArticleById")
	public Map<String,Object> getArticleById(int articleId,
			@RequestParam(defaultValue="true",required = false)boolean isRead) {
		Map<String,Object> result = new HashMap<>();
		result.put("article",articleService.getArticleById(articleId));
		if(isRead) {
			articleService.addReadAmounts(articleId);
		}
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updateArticleFull")
	public Map<String,Object> updateNewsFull(Article article,HttpServletRequest request) {
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	    MultipartFile file = multipartRequest.getFile("illuatrationPicture");   
	    boolean isUpload = true;
	    if(file != null && file.getSize() > 0) {
	        String fileOriginName = file.getOriginalFilename();
	        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
	        String fileName = /*article.getArticleTitle() +*/ sdf.format(new Date()) + fileType;
			isUpload= MyTools.uploadFile(file, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/article/" + fileName);
			article.setIlluatrationPictureSrc("JZTResource/image/article/" + fileName);
	    }
		if(articleService.updateArticle(article) > 0 && isUpload == true) {
			isSuccess = true;
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updateArticleWithoutFile")
	public Map<String,Object> updateArticleWithoutFile(Article article) {
		Map<String,Object> result = new HashMap<>();
		result.put("isSuccess", articleService.updateArticle(article));
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/deleteArticleById")
	public Map<String,Object> deleteArticleById(int articleId) {
		Map<String,Object> result = new HashMap<>();
		result.put("isSuccess", articleService.deleteArticleById(articleId));
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/searchArticle")
	public Map<String,Object> searchArticle(Article article,
			@RequestParam(defaultValue="1",required=false)int page,
			@RequestParam(defaultValue="20",required=false)int displayCount,
			@RequestParam(defaultValue="null",required=false)String startDate,
			@RequestParam(defaultValue="null",required=false)String endDate) {
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
		Map<String,Object> result = new HashMap<>();
		result.put("articleList", articleService.search(article, page, displayCount, sd, ed));
		result.put("maxPage", articleService.getSearchedMaxPage(article,displayCount,sd,ed));
		result.put("currentPage", page);
		return result;
	}
}
