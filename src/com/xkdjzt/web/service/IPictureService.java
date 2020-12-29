package com.xkdjzt.web.service;

import java.util.List;

import com.xkdjzt.web.entity.Picture;

public interface IPictureService {
	public int addPicture(Picture picture);
	public int deletePictureById(int pictureId);
	public List<Picture> getIndexPictures();
	public int updatePictureById(Picture picture);
}
