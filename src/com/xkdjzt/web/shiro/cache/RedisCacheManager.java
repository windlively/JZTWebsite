package com.xkdjzt.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("shiroRedisCacheManager")
public class RedisCacheManager implements CacheManager{

	@SuppressWarnings("rawtypes")
	@Autowired
	private Cache redisCache;
	
	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(String str) throws CacheException {
		return redisCache;
	}

}
