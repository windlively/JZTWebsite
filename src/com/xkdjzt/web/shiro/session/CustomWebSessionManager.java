package com.xkdjzt.web.shiro.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

public class CustomWebSessionManager extends DefaultWebSessionManager {
	/**
	 * 优化：解决了频繁访问Redis数据库的问题
	 * 先从request中获取session，如果获取不到再从Redis中获取，然后添加到request中
	 */
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
		ServletRequest request = null;
		if (sessionKey instanceof WebSessionKey)
			request = ((WebSessionKey)sessionKey).getServletRequest();
		
		if(request != null && sessionId != null) {
			Session session = (Session) request.getAttribute(sessionId.toString());
			if (session != null)
				return session;
		}
		
		Session session = super.retrieveSession(sessionKey);
		if(request != null && sessionId != null)
			request.setAttribute(sessionId.toString(), session);
		
		return session;
		
	}
	
}
