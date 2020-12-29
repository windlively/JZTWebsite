package com.xkdjzt.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xkdjzt.web.entity.Picture;
import com.xkdjzt.web.service.IPictureService;
import com.xkdjzt.web.util.MyTools;

@Controller
public class PictureController {
	@Autowired
	private IPictureService pictureService;
	private ObjectMapper objMapper = new ObjectMapper();
	
	@RequiresRoles("administrator")
	@RequestMapping("/uploadIndexPicture")
	public void uploadIndexPicture(HttpServletRequest request,HttpServletResponse response,Picture picture) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile indexPicture = multipartRequest.getFile("indexPicture");
		boolean isUpload = false;
		if(indexPicture != null && indexPicture.getSize() > 0) {
			String fileOriginName = indexPicture.getOriginalFilename();
	        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf("."));
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd[HH-mm]");
	        String fileName = /*picture.getPictureName() +*/ sdf.format(new Date()) + fileType;
			isUpload= MyTools.uploadFile(indexPicture, new File(request.getServletContext().getRealPath("/")).getParent() + "/JZTResource/image/indexImage/" + fileName);
			picture.setPictureSrc("JZTResource/image/indexImage/" + fileName);
		}
		if(picture.getPictureInfo() == null) {
			picture.setPictureInfo("");
		}
		picture.setPictureType("index");
		if(isUpload && pictureService.addPicture(picture) > 0) {
			try {
				response.getWriter().write("{\"isSuccess\":1}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				response.getWriter().write("{\"isSuccess\":0}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("getIndexPictures")
	public void getIndexPictures(HttpServletRequest request,HttpServletResponse response) {
		List<Picture> pictureList = new ArrayList<>();
		pictureList = pictureService.getIndexPictures();
		try {
			response.getWriter().write("{\"pictureList\":"+ objMapper.writeValueAsString(pictureList) +"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequiresRoles("administrator")
	@RequestMapping("/updateIndexPicture")
	public void updateIndexPicture(HttpServletResponse response,Picture picture) {
		try {
			if(pictureService.updatePictureById(picture) > 0) {
				response.getWriter().write("{\"isSuccess\":1}");
			}else {
				response.getWriter().write("{\"isSuccess\":0}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequiresRoles("administrator")
	@RequestMapping("/deletePictureById")
	public void deletePictureById(HttpServletResponse response,int pictureId) {
		try {
			if(pictureService.deletePictureById(pictureId) > 0) {
				response.getWriter().write("{\"isSuccess\":1}");
			}else {
				response.getWriter().write("{\"isSuccess\":0}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
