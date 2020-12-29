package com.xkdjzt.web.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.xkdjzt.web.util.JedisUtil;
@Component
public class MyBatisRedisCache implements Cache {
	
	Logger log = Logger.getLogger(MyBatisRedisCache.class);
	
	private JedisUtil jedisUtil;
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String MYBATIS_CACHE_PERFIX = "mybatis-cache:";
	
	private String id;
	
	public MyBatisRedisCache() {}
	
	public MyBatisRedisCache(final String id) {
	   if (id == null) {
	     throw new IllegalArgumentException("Cache instances require an ID");
	   }
	   log.debug("set id;");
	   this.id = id;
	   jedisUtil = new JedisUtil();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		if(key == null || value == null)
			return;
		if(key instanceof String) 
			key = MYBATIS_CACHE_PERFIX + key;
		byte[] k = SerializationUtils.serialize(key);
		byte[] v = SerializationUtils.serialize(value);
		jedisUtil.set(k, v);
		jedisUtil.expire(k, 600);
		log.debug("set key: " + key + ",value: " + value + " to Redis");
	}

	@Override
	public Object getObject(Object key) {
		if(key == null)
			return null;
		byte[] k = SerializationUtils.serialize(key);
		byte[] v = jedisUtil.get(k);
		log.debug("get value " + SerializationUtils.deserialize(v) + " by key " + key);
		return SerializationUtils.deserialize(v);
	}

	@Override
	public Object removeObject(Object key) {
		if(key == null)
			return null;
		byte[] k = SerializationUtils.serialize(key);
		byte[] v = jedisUtil.get(k);
		jedisUtil.del(k);
		log.debug("remove key " + key);
		return SerializationUtils.deserialize(v);
	}

	@Override
	public void clear() {
		jedisUtil.clear(MYBATIS_CACHE_PERFIX + "*");
	}

	@Override
	public int getSize() {
		return (int) jedisUtil.size(MYBATIS_CACHE_PERFIX + "*");
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
