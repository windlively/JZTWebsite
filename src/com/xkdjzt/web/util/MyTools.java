package com.xkdjzt.web.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class MyTools {
	public static boolean uploadFile(MultipartFile multipartFile,String filePath) {
		try{
			File file = new File(filePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			multipartFile.transferTo(file);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
