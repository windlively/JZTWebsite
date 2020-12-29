package com.xkdjzt.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.xkdjzt.web.util.JedisUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource(value="classpath:redis.properties")
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${redis.host}")
	private String REDIS_HOST;
	@Value("${redis.port}")
	private int REDIS_PORT;
	@Value("${redis.password}")
	private String REDIS_PASSWORD;

	
	//Redis相关配置
	@Bean
	public JedisPool jedisPool() {
		JedisPool jedisPool = new JedisPool(jedisPoolConfig(),REDIS_HOST,REDIS_PORT, 2000, REDIS_PASSWORD);
		return jedisPool;
	}
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig= new JedisPoolConfig();
		return jedisPoolConfig;
	}

	@Bean(name="jedisUtil")
	public JedisUtil jedisUtil() {
		JedisUtil jedisUtil = new JedisUtil();
		jedisUtil.setJedisPool(jedisPool());
		return jedisUtil;
	}
	
}
