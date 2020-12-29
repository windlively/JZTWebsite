package com.xkdjzt.web.util;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
	
	private static JedisPool jedisPool;

	public void setJedisPool(JedisPool jedisPool) {
		JedisUtil.jedisPool = jedisPool;
	}
	
	//设置k-v
	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set(key, value);
			return value;
		}finally {
			jedis.close();
		}
		
	}
	//设置超时时间
	public void expire(byte[] key, int i) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.expire(key, i);
		}finally {
			jedis.close();
		}
	}
	//获取val
	public byte[] get(byte[] key) {
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.get(key);
		}finally {
			jedis.close();
		}
	}
	//删除
	public void del(byte[] key) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.del(key);
		}finally {
			jedis.close();
		}
		
	}
	//获取所有的keys
	public Set<byte[]> keys(String str) {
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.keys(str.getBytes());
		}finally {
			jedis.close();
		}
	}
	
	public void clear(String str) {
		Jedis jedis = jedisPool.getResource();
		Set<byte[]> keys = keys(str);
		try {
			if(keys.isEmpty())
				return;
			keys.forEach(key -> {
				jedis.del(key);
			});
		}finally {
			jedis.close();
		}
	}
	
	public int size(String str) {
		return keys(str).size();
	}
	
	public long size() {
		Jedis jedis = jedisPool.getResource();
		long size = jedis.dbSize();
		return size;
	}
}
