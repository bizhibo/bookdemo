package com.book.demo.car.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.car.model.Goods;
import com.book.demo.car.redis.RedisUtil;
import com.book.demo.car.util.JsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisTest {

	private Jedis jedis;

	@Before
	// 注解before 表示在方法前执行
	public void initJedis() throws IOException {
		jedis = RedisUtil.initPool().getResource();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveCarTest() {
		Pipeline pipeline = jedis.pipelined();// 开启redis管道
		pipeline.hmset("car",  this.initGoods());
		pipeline.expire("car", 10 * 60);
		System.out.println(pipeline.syncAndReturnAll());// 提交本次操作
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void queryCarTest() {
		System.out.println(jedis.hgetAll("car"));
	}

	@After
	public void closeJedis() {
		jedis.close();
	}

	/**
	 * @描述 : 初始化商品信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日上午11:56:36
	 *
	 * @return
	 */
	private Map<String, String> initGoods() {
		Map<String, String> map = new HashMap<String, String>();
		Goods goods1 = new Goods();
		goods1.setAdInfo("<html></html>");
		goods1.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品产地：中国大陆");
		goods1.setId(4380877);
		goods1.setSpecificationsInfo("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统操作系统Windows 10家庭版处理器CPU类型Intel 第7代 酷睿CPU速度2.5GHz三级缓存6M其它说明I5-7300HQ芯片组芯片组其它　");
		Goods goods2 = new Goods();
		goods2.setAdInfo("<html></html>");
		goods2.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品产地：中国大陆");
		goods2.setId(4380878);
		goods2.setSpecificationsInfo("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统操作系统Windows 10家庭版处理器CPU类型Intel 第7代 酷睿CPU速度2.5GHz三级缓存6M其它说明I5-7300HQ芯片组芯片组其它　");
		map.put(goods1.getId() + "", JsonUtil.toJson(goods1));
		map.put(goods2.getId() + "", JsonUtil.toJson(goods2));
		return map;
	}
}
