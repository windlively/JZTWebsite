package com.xkdjzt.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xkdjzt.web.dao.IAdministratorDao;
import com.xkdjzt.web.entity.Administrator;
import com.xkdjzt.web.service.IAdministratorService;

@Service
public class AdministratorService implements IAdministratorService {

	@Autowired
	private IAdministratorDao administratorDao;
	@Override
	public Administrator checkAdministrator(Administrator administrator) {
		System.out.println(administrator.getAdminName() + administrator.getAdminName());
		Administrator admin = null;
		if(administratorDao.checkAdministrator(administrator) != null)
			admin = administratorDao.checkAdministrator(administrator);
		return admin;
	}

}
