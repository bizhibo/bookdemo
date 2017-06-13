package com.book.demo.session.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.util.SafeEncoder;

import com.book.demo.session.model.RedisSession;
import com.book.demo.session.redis.RedisUtil;
import com.book.demo.session.util.JsonUtil;
import com.book.demo.session.util.TranscoderUtils;

public class RedisTest {

	private Jedis jedis;

	@Before
	// 注解before 表示在方法前执行
	public void initJedis() throws IOException {
		jedis = RedisUtil.initPool().getResource();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveSessionTest() {
		RedisSession session = new RedisSession();
		session.setAttribute("map", new HashMap<String, String>());
		session.setAttribute("list", new ArrayList<Object>());
		session.setAttribute("date", new Date());
		Pipeline pipeline = jedis.pipelined();// 开启redis管道
		// 采用管道形式提交命令,setex方法可设置redis中key的生命周期
		// SafeEncoder redis提供的解码器
		// TranscoderUtils 对java对象解压缩工具类
		pipeline.setex(SafeEncoder.encode("session"), 10 * 60,
				TranscoderUtils.encodeObject(session));
		System.out.println(pipeline.syncAndReturnAll());// 提交本次操作
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void querySessionTest() {
		RedisSession session = (RedisSession) TranscoderUtils
				.decodeObject(jedis.get(SafeEncoder.encode("session")));
		System.out.println(JsonUtil.toJson(session));
	}

	@After
	public void closeJedis() {
		jedis.close();
	}
	
	public static void main(String[] args) {
		RedisSession session = new RedisSession();
		Map<String, String> ma = new HashMap<String, String>();
		ma.put("userName", "zhangsan");
		session.setAttribute("map", ma);
		session.setAttribute("list", new ArrayList<Object>());
		session.setAttribute("date", new Date());
		System.out.println(JsonUtil.toJson(session));
	}

}
