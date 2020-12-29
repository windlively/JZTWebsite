package com.xkdjzt.web.shiro.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.xkdjzt.web.util.JedisUtil;

@Component
public class ShiroRedisCache<K,V> implements Cache<K,V> {

	Logger logger = Logger.getLogger(ShiroRedisCache.class);
	
	@Autowired
	private JedisUtil jedisUtil;
	
	private final String SHIRO_CACHE_PERFIX = "shiro-cache:";
	
	private byte[] getKey(K k) {
		if(k instanceof String)
			return (SHIRO_CACHE_PERFIX + k).getBytes();
		return SerializationUtils.serialize(k);
	}
	
	
	@Override
	public void clear() throws CacheException {
		jedisUtil.clear(SHIRO_CACHE_PERFIX + "*");
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K k) throws CacheException {
		logger.debug("从Redis中获取权限数据");
		byte[] value = jedisUtil.get(getKey(k));
		if(value != null) {
			return (V) SerializationUtils.deserialize(value);
		}
		logger.debug("Redis中没有权限数据");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		Set<byte[]> byteKeys = jedisUtil.keys(SHIRO_CACHE_PERFIX + "*");
		Set<K> keys = new HashSet<>();
		byteKeys.forEach(key -> {
			keys.add((K) SerializationUtils.deserialize(key));
		});
		return keys;
	}

	@Override
	public V put(K k, V v) throws CacheException {
		byte[] key = getKey(k);
		byte[] value = SerializationUtils.serialize(v);
		jedisUtil.set(key, value);
		jedisUtil.expire(key, 600);
		return v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(K k) throws CacheException {
		byte[] key = getKey(k);
		byte[] value = jedisUtil.get(key);
		jedisUtil.del(key);
		if(value != null)
			return (V)SerializationUtils.deserialize(value);
		
		return null;
	}

	@Override
	public int size() {
		return jedisUtil.size(SHIRO_CACHE_PERFIX + "*"); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		Set<byte[]> keys = jedisUtil.keys(SHIRO_CACHE_PERFIX + "*");
		Set<V> values = new HashSet<>();
		if(keys.isEmpty())
			return values;
		keys.forEach(key -> {
			values.add((V)SerializationUtils.deserialize(key));
		});
		return values;
	}


}
