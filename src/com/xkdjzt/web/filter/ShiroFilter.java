package com.xkdjzt.web.filter;

import javax.servlet.annotation.WebFilter;

import org.springframework.web.filter.DelegatingFilterProxy;

@WebFilter(filterName="shiroFilter",urlPatterns="/*")
public class ShiroFilter extends DelegatingFilterProxy{
	
}
