package com.xkdjzt.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xkdjzt.web.entity.Administrator;

@Controller
public class ManagerController {
	
	@RequestMapping("/checkLogin")
	public String checkLogin(Administrator admin,HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(admin.getAdminName(),admin.getAdminPassword());
		try {
			token.setRememberMe(true);
			subject.login(token);
		}catch(AuthenticationException ex) {
			ex.printStackTrace();
			return "/error";
		}
		return "redirect:/index";
	}
}
