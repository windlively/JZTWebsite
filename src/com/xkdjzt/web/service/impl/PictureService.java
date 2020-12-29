package com.xkdjzt.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xkdjzt.web.dao.IPictureDao;
import com.xkdjzt.web.entity.Picture;
import com.xkdjzt.web.service.IPictureService;
@Service
public class PictureService implements IPictureService {
	@Autowired
	private IPictureDao pictureDao;
	@Override
	public int addPicture(Picture picture) {
		return pictureDao.addPicture(picture);
	}

	@Override
	public int deletePictureById(int pictureId) {
		return pictureDao.deletePictureById(pictureId);
	}

	@Override
	public List<Picture> getIndexPictures() {
		return pictureDao.getIndexPictures();
	}

	@Override
	public int updatePictureById(Picture picture) {
		return pictureDao.updatePictureById(picture);
	}
}
