package com.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Testzjl {
	public static void main(String[] args) {
		Testzjl test = new Testzjl();
		test.linkOneRedis();
	}
	public void linkOneRedis(){
		
		//创建jedis对象，连接redis服务器
		String host="192.168.56.132";
		int port = 6379;
		Jedis jedis = new Jedis(host, port);
		//向redis服务器设置值
		jedis.set("username", "zhangjialou");
		//获取Username
		String value = jedis.get("username");
		
		System.out.println(value);
		
		
		
	}

}
