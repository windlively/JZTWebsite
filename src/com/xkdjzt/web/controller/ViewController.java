package com.xkdjzt.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/login")
	public String managerLoginView() {
		return "login";
	}
	
	@RequestMapping("/error")
	public String errorView() {
		return "error";
	}

	@RequestMapping("/index")
	public String indexView(HttpServletRequest request) {
		return "index";
	}

}
