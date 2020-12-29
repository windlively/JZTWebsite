package com.xkdjzt.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkdjzt.web.entity.AboutsOrganization;
import com.xkdjzt.web.entity.AboutsTeam;
import com.xkdjzt.web.service.IAboutsService;

@Controller
public class AboutsController {
	@Autowired
	private IAboutsService aboutsService;
	
	@ResponseBody
	@RequestMapping("/getAboutsOrganization")
	public Map<String,Object> getAboutsOrganization(){
		Map<String,Object> result = new HashMap<>();
		result.put("aboutsOrganization", aboutsService.getAboutsOrganization());
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updateAboutsOrganization")
	public Map<String,Object> updateAboutsOrganization(AboutsOrganization aboutsOrganization){
		Map<String,Object> result = new HashMap<>();
		result.put("isSuccess", aboutsService.updateAboutsOrganization(aboutsOrganization) > 0 ? 1 : 0);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getAboutsTeam")
	public Map<String,Object> getAboutsTeam(){
		Map<String,Object> result = new HashMap<>();
		result.put("aboutsTeam", aboutsService.getAboutsTeam());
		return result;
	}
	
	@RequiresRoles("administrator")
	@ResponseBody
	@RequestMapping("/updateAboutsTeam")
	public Map<String,Object> updateAboutsTeam(AboutsTeam aboutsTeam){
		Map<String,Object> result = new HashMap<>();
		result.put("isSuccess", aboutsService.updateAboutsTeam(aboutsTeam) > 0 ? 1 : 0);
		return result;
	}
}
