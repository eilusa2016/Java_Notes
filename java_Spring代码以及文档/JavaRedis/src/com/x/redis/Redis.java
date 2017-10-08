package com.x.redis;

import redis.clients.jedis.Jedis;

public class Redis {
	public static void main(String[] args) {
		new Redis().getconn();
	}
	
	public void  getconn(){
		//连接本地的 Redis 服务
	      Jedis jedis = new Jedis("localhost");
	      //System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	     // System.out.println("Server is running: "+jedis.ping());
	      
	      System.out.println(jedis.get("xjb"));
	}
	
}
