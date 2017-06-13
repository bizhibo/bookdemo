package com.book.demo.ad.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @描述 : 连接redis集群工具
 * @创建者：liushengsong
 * @创建时间： 2017年2月17日上午10:53:14
 *
 */
public class RedisClusterUtil {

	/**
	 * @描述 : redisCluster初始化方法
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月18日下午5:24:12
	 *
	 * @return
	 * @throws IOException
	 */
	public static JedisCluster initCluster() throws IOException {
		// 加载redis配置文件
		InputStream inputStream = RedisClusterUtil.class.getClass()
				.getResourceAsStream("/redis-config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		// 初始化redisCluster连接池配置
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxTotal(Integer.valueOf(properties
				.getProperty("maxActive")));
		genericObjectPoolConfig.setMaxIdle(Integer.valueOf(properties
				.getProperty("maxIdle")));
		genericObjectPoolConfig.setMaxWaitMillis(Long.valueOf(properties
				.getProperty("maxWait")));
		genericObjectPoolConfig.setTestOnBorrow(Boolean.valueOf(properties
				.getProperty("testOnBorrow")));
		// 出事换redis连接地址
		Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
		String[] ips = properties.getProperty("cluster.ip").split("(?:\\s|,)+");
		for (int i = 0; i < ips.length; i++) {
			String[] address = ips[i].split(":");
			hostAndPorts.add(new HostAndPort(address[0], Integer
					.valueOf(address[1])));
		}
		// 返回redisCluster
		return new JedisCluster(hostAndPorts, Integer.valueOf(properties
				.getProperty("timeout")), genericObjectPoolConfig);
	}

}
