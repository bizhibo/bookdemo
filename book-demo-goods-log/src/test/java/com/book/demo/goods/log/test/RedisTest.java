package com.book.demo.goods.log.test;

import java.io.IOException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.book.demo.goods.log.model.GoodsLog;
import com.book.demo.goods.log.redis.RedisUtil;
import com.book.demo.goods.log.util.JsonUtil;
import com.book.demo.goods.log.util.UUIDUtil;

public class RedisTest {

	private Jedis jedis;

	@Before
	// 注解before 表示在方法前执行
	public void initJedis() throws IOException {
		jedis = RedisUtil.initPool().getResource();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveGoodsLogTest() {
		Pipeline pipeline = jedis.pipelined();// 开启redis管道
		pipeline.sadd("goods-log", this.initGoodsLog());
		pipeline.expire("goods-log", 10 * 60);
		System.out.println(pipeline.syncAndReturnAll());// 提交本次操作
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void queryGoodsLogTest() {
		System.out.println(jedis.smembers("goods-log"));
	}

	@After
	public void closeJedis() {
		jedis.close();
	}

	/**
	 * @描述 : 初始化商品日志
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日上午11:56:36
	 *
	 * @return
	 */
	private String[] initGoodsLog() {
		GoodsLog goodsLog1 = new GoodsLog();
		goodsLog1.setClickDate(new Date());
		goodsLog1.setId(7768);
		goodsLog1.setIp("172.54.87.9");
		goodsLog1.setUuid(UUIDUtil.upperUUID());
		GoodsLog goodsLog2 = new GoodsLog();
		goodsLog2.setClickDate(new Date());
		goodsLog2.setId(7769);
		goodsLog2.setIp("172.54.87.9");
		goodsLog2.setUuid(UUIDUtil.upperUUID());
		String[] goodsLogs = new String[2];
		goodsLogs[0] = JsonUtil.toJson(goodsLog1);
		goodsLogs[1] = JsonUtil.toJson(goodsLog2);
		return goodsLogs;
	}
}
