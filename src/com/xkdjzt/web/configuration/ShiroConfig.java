package com.xkdjzt.web.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xkdjzt.web.shiro.session.CustomWebSessionManager;

@Configuration
public class ShiroConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		Map<String,String> map = new HashMap<>();
		map.put("WebView/*", "anon");
		map.put("/get*", "anon");
		map.put("/index", "roles[administrator]");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}
	
	@Autowired
	@Qualifier("shiroRedisCacheManager")
	private CacheManager redisCacheManager;
	
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager dwm = new DefaultWebSecurityManager();
		dwm.setRealm(jdbcRealm());
		dwm.setCacheManager(redisCacheManager);
		dwm.setSessionManager(shiroSessionManager());
		dwm.setRememberMeManager(cookieRememberMeManager());
		return dwm;
	}
	@Bean
	public Realm jdbcRealm() {
		JdbcRealm jr = new JdbcRealm();
		jr.setAuthenticationQuery("SELECT adminPassword FROM administrator WHERE adminName = ?");
		jr.setUserRolesQuery("SELECT role FROM administrator WHERE adminName = ?");
		jr.setDataSource(dataSource);
		jr.setCredentialsMatcher(credentialsMatcher());
		return jr;
	}
	@Bean
	public HashedCredentialsMatcher credentialsMatcher() {
		HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
		hcm.setHashAlgorithmName("md5");
		hcm.setHashIterations(3);
		return hcm;
	}
	//shiro的session配置
	@Autowired
	private SessionDAO redisSessionDao;
	
	@Bean
	public CustomWebSessionManager shiroSessionManager() {
		//使用自定义的sessionManager，默认的DefaultWebSessionManager会频繁访问Redis
		CustomWebSessionManager sessionManager = new CustomWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDao);
		return sessionManager;
	}
	//shiro的RememberMe（自动登录）配置
	public CookieRememberMeManager cookieRememberMeManager() {
		CookieRememberMeManager crmm = new CookieRememberMeManager();
		crmm.setCookie(simpleCookie());
		return crmm;
	}
	
	public Cookie simpleCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setMaxAge(60*60*24);
		return cookie;
	}

}
