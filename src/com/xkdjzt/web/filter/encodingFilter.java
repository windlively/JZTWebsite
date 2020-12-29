package com.xkdjzt.web.filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Servlet Filter implementation class encodingFilter
 */
@WebFilter(filterName = "encodingFilter",
			initParams = {@WebInitParam(name="forceEncoding",value="true"),
					@WebInitParam(name="encoding",value="UTF-8")},
			urlPatterns="/*")
public class encodingFilter extends CharacterEncodingFilter{
	
}
