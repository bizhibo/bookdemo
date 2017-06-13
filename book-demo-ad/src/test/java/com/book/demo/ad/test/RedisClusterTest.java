package com.book.demo.ad.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.JedisCluster;

import com.book.demo.ad.model.AdContent;
import com.book.demo.ad.model.Advertisement;
import com.book.demo.ad.model.Template;
import com.book.demo.ad.redis.RedisClusterUtil;
import com.book.demo.ad.util.JsonUtil;

public class RedisClusterTest {

	private JedisCluster jedisCluster;

	@Before
	// 注解before 表示在方法前执行
	public void initJedis() throws IOException {
		jedisCluster = RedisClusterUtil.initCluster();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveAdTest() {
		// 由于clustr不支持byte与管道所以我们采用json的形式存储对象
		String jsonStr = JsonUtil.toJson(this.initAdvertisement());
		System.out.println(jedisCluster.setex("test", 10 * 60, jsonStr));// 提交本次操作
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void queryAdTest() {
		Advertisement advertisement = JsonUtil.fromJson(
				jedisCluster.get("test"), Advertisement.class);
		System.out.println(advertisement.getId());
	}

	@After
	public void closeJedis() {
		jedisCluster.close();
	}

	/**
	 * @描述 : 初始化广告数据
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月18日下午3:53:17
	 *
	 * @return
	 */
	private Advertisement initAdvertisement() {
		Template template = new Template();
		template.setId(20);
		template.setName("轮播模版");
		template.setScript("alert('轮播')");
		AdContent adContent1 = new AdContent();
		adContent1.setId(1);
		adContent1.setName("新年图书忒大促.");
		adContent1.setSequence(1);
		adContent1.setUrl("https://book.jd.com/");
		adContent1.setImageUrl("http://book.image.com/test.jpg");
		AdContent adContent2 = new AdContent();
		adContent2.setId(2);
		adContent2.setName("手机专场，满1000返50.");
		adContent2.setSequence(2);
		adContent2.setUrl("https://shouji.jd.com/");
		adContent2.setImageUrl("http://book.image.com/test.jpg");
		List<AdContent> adContents = new ArrayList<AdContent>();
		adContents.add(adContent1);
		adContents.add(adContent2);
		Advertisement advertisement = new Advertisement();
		advertisement.setId(10001);
		advertisement.setPositionCode("home-01");
		advertisement.setTid(template.getId());
		advertisement.setAdContents(adContents);
		return advertisement;
	}

}
