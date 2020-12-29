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

import com.xkdjzt.web.entity.PoetryReview;
import com.xkdjzt.web.service.IPoetryReviewService;
import com.xkdjzt.web.util.MyTools;

@Controller
public class PoetryReviewController {
	@Autowired
	private IPoetryReviewService poetryReviewService;
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/uploadPoetryReview")
	public Map<String,Object> uoloadPoetryReview(PoetryReview poetryReview,HttpServletRequest request) {
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("illuatrationPicture"); 
        boolean isUpload = true;
        if(file != null && file.getSize() > 0) {
	        String fileOriginName = file.getOriginalFilename();
	        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm]");
	        String fileName = sdf.format(new Date()) + fileType;
			isUpload= MyTools.uploadFile(file, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/poetryReview/" + fileName);
			poetryReview.setIlluatrationPictureSrc("JZTResource/image/poetryReview/" + fileName);
        }
		if(isUpload) {
			poetryReview.setReadAmounts(0);
			poetryReview.setUploadTime(new Date());
			poetryReview.setUpdateTime(new Date());
			if(poetryReviewService.addPoetryReview(poetryReview) > 0) {
				isSuccess = true;
			}
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getPoetryReviewList")
	public Map<String, Object> getPoetryReviewListByPage(HttpServletRequest request,
			@RequestParam(defaultValue = "1",required = false)int page,
			@RequestParam(defaultValue = "20",required = false)int displayCount,
			@RequestParam(defaultValue = "all",required = false,value = "type")String poetryType,
			@RequestParam(defaultValue = "false",required = false)boolean useSessionPage) {
		Map<String,Object> result = new HashMap<>();
		if(useSessionPage == true && request.getSession().getAttribute("currentPage") != null) {
			page = (int) request.getSession().getAttribute("currentPage");
		}
		request.getSession().setAttribute("currentPage", page);
		List<PoetryReview> poetryReviewList = null;
		PoetryReview pr = new PoetryReview();
		if("原创诗".equals(poetryType) || "古体诗".equals(poetryType) || "近现代诗".equals(poetryType)) {
			pr.setPoetryType(poetryType);
			poetryReviewList = poetryReviewService.search(pr, page, displayCount, null, null);
			result.put("maxPage", poetryReviewService.getSearchedMaxPage(pr, displayCount, null, null));
		}else {
			poetryReviewList = poetryReviewService.getPoetryReviewListByPage(page, displayCount, null);
			result.put("maxPage", poetryReviewService.getMaxPage(displayCount,poetryType));
		}
		result.put("poetryReviewList", poetryReviewList);
		result.put("currentPage", page);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getPoetryReviewById")
	public Map<String, Object> getPoetryReviewById(int reviewId,
			@RequestParam(defaultValue="true",required = false)boolean isRead) {
		Map<String, Object> result = new HashMap<>();
		result.put("poetryReview", poetryReviewService.getPoetryReviewById(reviewId));
		if(isRead) {
			poetryReviewService.addReadAmounts(reviewId);
		}
		return result;
	}
	
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updatePoetryReviewFull")
	public Map<String, Object> updatePoetryReviewFull(PoetryReview poetryReview,HttpServletRequest request) {
		Map<String,Object> result = new HashMap<>();
		boolean isSuccess = false;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	    MultipartFile file = multipartRequest.getFile("illuatrationPicture");   
	    boolean isUpload = true;
	    if(file != null && file.getSize() > 0) {
	        String fileOriginName = file.getOriginalFilename();
	        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm-ss]");
	        String fileName = /*poetryReview.getReviewTitle() +*/ sdf.format(new Date()) + fileType;
	        if("原创诗".equals(poetryReview.getPoetryType())) {
	        	fileName = /*poetryReview.getPoetryTitle() +*/ sdf.format(new Date()) + fileType;
	        }
			isUpload= MyTools.uploadFile(file, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/poetryReview/" + fileName);
			poetryReview.setIlluatrationPictureSrc("JZTResource/image/poetryReview/" + fileName);
	    }
		if(poetryReviewService.updatePoetryReview(poetryReview) > 0 && isUpload == true) {
			isSuccess = true;
		}
		result.put("isSuccess", isSuccess ? 1 : 0);
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updatePoetryReviewWithoutFile")
	public Map<String, Object> updatePoetryReviewWithoutFile(PoetryReview poetryReview) {
		Map<String,Object> result = new HashMap<>();
		result.put("isSuccess", poetryReviewService.updatePoetryReview(poetryReview));
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/deletePoetryReviewById")
	public Map<String, Object> deletePoetryReviewById(int reviewId) {
		Map<String,Object> result = new HashMap<>();
		result.put("isSuccess", poetryReviewService.deletePoetryReview(reviewId));
		return result;
	}
	
	@ResponseBody
	@RequestMapping("searchPoetryReview")
	public Map<String, Object> searchPoetryReview(HttpServletRequest request,PoetryReview poetryReview,
			@RequestParam(defaultValue = "1",required = false)int page,
			@RequestParam(required = false)String entityType,
			@RequestParam(defaultValue = "20",required = false)int displayCount,
			@RequestParam(defaultValue="null",required=false)String startDate,
			@RequestParam(defaultValue="null",required=false)String endDate) {
		if("poetry".equals(entityType))
			poetryReview.setPoetryType("原创诗");
		if("poetryReview".equals(entityType))
			poetryReview.setPoetryType("allPoetryReview");
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
		result.put("poetryReviewList",poetryReviewService.search(poetryReview, page, displayCount, sd, ed));
		result.put("maxPage", poetryReviewService.getSearchedMaxPage(poetryReview, displayCount, sd, ed));
		result.put("currentPage", page);
		return result;
	};
}
