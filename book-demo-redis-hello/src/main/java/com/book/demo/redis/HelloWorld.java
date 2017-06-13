package com.book.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class HelloWorld {

	public static void main(String[] args) {
		JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("test", "a1");
		jedis.get("test");
		jedis.del("test");
		jedis.close();
		jedisPool.close();
		
		
		Long flag = jedis.setnx("这里是从数据库取到的最小的序号", "这里的值也可以是最小序号");
		if(flag>0){
			/**继续你的业务代码**/
		}else{
			/**说明该最小值被占用**/
		}
	}
}
