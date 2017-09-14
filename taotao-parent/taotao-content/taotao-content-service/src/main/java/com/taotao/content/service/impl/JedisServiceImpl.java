package com.taotao.content.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

import com.taotao.content.service.JedisService;

public class JedisServiceImpl implements JedisService {
	
	@Autowired
	private JedisCluster jedisCluster;

	public String hget(final String key, final String field) {
		return jedisCluster.hget(key, field);
	}
	
	public Long hdel(final String key, final String field) {
		return jedisCluster.hdel(key, field);
	}

	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}
	
	public String hmset(String key, Map<String, String> hash) {
		return jedisCluster.hmset(key, hash);
	}

	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	public String get(String key) {
		return jedisCluster.get(key);
	}

	public Long incrBy(String key, long integer) {
		return jedisCluster.incrBy(key, integer);
	}

	public String setex(String key, int seconds, String value) {
		return jedisCluster.setex(key, seconds, value);
	}

	public Long setnx(String key, String value) {

		return jedisCluster.setnx(key, value);
	}

	public Long del(String key) {
		return jedisCluster.del(key);
	}

	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	public Map<String, String> hgetAll(String key) {
		return jedisCluster.hgetAll(key);
	}

	public Long hincrBy(String key, String field, long value) {

		return jedisCluster.hincrBy(key, field, value);
	}

	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

}
