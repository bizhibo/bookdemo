package com.book.demo.redis.app.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @描述 : 利用连接池连接单个redis工具
 * @创建者：liushengsong
 * @创建时间： 2017年2月17日上午10:52:50
 *
 */
public class RedisUtil {

	/**
	 * @描述 : 初始化redis连接池
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月18日下午5:24:46
	 *
	 * @return
	 * @throws IOException
	 */
	public static JedisPool initPool() throws IOException {
		// 加载redis配置文件
		InputStream inputStream = RedisUtil.class.getClass()
				.getResourceAsStream("/redis-config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		// 初始化redis连接池配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(properties.getProperty("maxActive")));
		config.setMaxIdle(Integer.valueOf(properties.getProperty("maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(properties.getProperty("maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(properties
				.getProperty("testOnBorrow")));
		String[] address = properties.getProperty("ip").split(":");
		// 初始化redis连接池
		JedisPool pool = new JedisPool(config, address[0],
				Integer.valueOf(address[1]), Integer.valueOf(properties
						.getProperty("timeout")));
		return pool;
	}
}
