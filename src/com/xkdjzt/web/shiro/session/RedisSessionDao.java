package com.xkdjzt.web.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.xkdjzt.web.util.JedisUtil;

@Component
public class RedisSessionDao extends AbstractSessionDAO {

	@Autowired
	private JedisUtil jedisUtil;
	
	Logger log = Logger.getLogger(RedisSessionDao.class);
	/**
	 * 在Redis中存储时的key值的前缀
	 */
	private final String SHIRO_SESSION_PERFIX = "shiro-session:";
	
	/**
	 * 将String类型的key转换为byte数组
	 * @param key
	 * @return
	 */
	private byte[] getKey(String key) {
		return (SHIRO_SESSION_PERFIX + key).getBytes();
	}
	
	/**
	 * 保存session
	 * @param session
	 */
	private void saveSession(Session session) {
		if(session != null && session.getId() != null) {
			byte[] key = getKey(session.getId().toString());
			byte[] value = SerializationUtils.serialize(session);
			jedisUtil.set(key,value);
			jedisUtil.expire(key,600);
		}
		
	}
	
	
	/**
	 * 更新session
	 */
	@Override
	public void update(Session session) throws UnknownSessionException {
		saveSession(session);
	}
	
	/**
	 * 删除session
	 */
	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null)
			return;
		byte[] key = getKey(session.getId().toString());
		jedisUtil.del(key);
		
	}
	
	/**
	 * 获取所有session
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PERFIX + "*");
		Set<Session> sessions = new HashSet<>();
		if(keys.isEmpty())
			return sessions;
		keys.forEach(key -> {
			Session session = (Session)SerializationUtils.deserialize(jedisUtil.get(key));
			sessions.add(session);
		});
		return sessions;
	}

	/**
	 * 创建Session
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		saveSession(session);
		return sessionId;
	}

	/**
	 * 获取session
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		log.debug("read redis Session");
		if(sessionId == null)
			return null;
		byte[] key = getKey(sessionId.toString());
		byte[] value = jedisUtil.get(key);
		return (Session) SerializationUtils.deserialize(value);
	}

}
