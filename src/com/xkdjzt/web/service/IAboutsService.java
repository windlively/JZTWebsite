package com.xkdjzt.web.service;

import com.xkdjzt.web.entity.AboutsOrganization;
import com.xkdjzt.web.entity.AboutsTeam;

public interface IAboutsService {

	public AboutsOrganization getAboutsOrganization();

	public int updateAboutsOrganization(AboutsOrganization aboutsOrganization);

	public AboutsTeam getAboutsTeam();

	public int updateAboutsTeam(AboutsTeam aboutsTeam);

}
