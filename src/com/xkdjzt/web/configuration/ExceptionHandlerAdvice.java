package com.xkdjzt.web.configuration;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler({UnauthenticatedException.class,AuthenticationException.class})
	public ModelAndView shiroException(Exception ex,WebRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ex.getMessage());
		mv.setViewName("/error");
		return mv;
	} 
}
