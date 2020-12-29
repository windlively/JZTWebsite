package com.xkdjzt.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xkdjzt.web.dao.IAboutsDao;
import com.xkdjzt.web.entity.AboutsOrganization;
import com.xkdjzt.web.entity.AboutsTeam;
import com.xkdjzt.web.service.IAboutsService;

@Service
public class AboutsService implements IAboutsService {

	@Autowired
	private IAboutsDao aboutsDao;
	
	@Override
	public AboutsOrganization getAboutsOrganization() {
		return aboutsDao.getAboutsOrganization();
	}
	
	@Transactional
	@Override
	public int updateAboutsOrganization(AboutsOrganization aboutsOrganization) {
		return aboutsDao.updateAboutsOrganization(aboutsOrganization);
	}
	
	@Transactional
	@Override
	public AboutsTeam getAboutsTeam() {
		return aboutsDao.getAboutsTeam();
	}
	
	@Override
	public int updateAboutsTeam(AboutsTeam aboutsTeam) {
		return aboutsDao.updateAboutsTeam(aboutsTeam);
	}

}
