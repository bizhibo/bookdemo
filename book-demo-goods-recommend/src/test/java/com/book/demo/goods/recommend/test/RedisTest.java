package com.book.demo.goods.recommend.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.book.demo.goods.recommend.model.Goods;
import com.book.demo.goods.recommend.redis.RedisUtil;
import com.book.demo.goods.recommend.util.JsonUtil;

public class RedisTest {

	private Jedis jedis;

	@Before
	// 注解before 表示在方法前执行
	public void initJedis() throws IOException {
		jedis = RedisUtil.initPool().getResource();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveGoodsRecommendTest() {
		Pipeline pipeline = jedis.pipelined();// 开启redis管道
		pipeline.lpush("goods-recommend", this.initGoods());
		pipeline.expire("goods-recommend", 10 * 60);
		System.out.println(pipeline.syncAndReturnAll());// 提交本次操作
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void queryGoodsRecommendTest() {
		System.out.println(jedis.lrange("goods-recommend", 0,
				9));
		System.out.println(jedis.lrange("goods-recommend", 10,
				20));
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
	private String[] initGoods() {
		Goods goods = new Goods();
		goods.setAdInfo("<html></html>");
		goods.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品产地：中国大陆");
		goods.setId(4380878);
		goods.setSpecificationsInfo("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统操作系统Windows 10家庭版处理器CPU类型Intel 第7代 酷睿CPU速度2.5GHz三级缓存6M其它说明I5-7300HQ芯片组芯片组其它　");
		String[] goodsArray = new String[20];
		for (int i = 0; i < 20; i++) {
			goodsArray[i] = JsonUtil.toJson(goods);
		}
		return goodsArray;
	}
}
