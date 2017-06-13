package com.book.demo.clicks.test;

import java.io.IOException;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.clicks.model.ClicksLog;
import com.book.demo.clicks.mongo.MongoDBUtil;
import com.book.demo.clicks.util.ConvertUtil;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

public class MongoDBTest {

	private MongoClient mongoClient;

	@Before
	// 注解before 表示在方法前执行
	public void initMongoClient() throws IOException {
		mongoClient = MongoDBUtil.initMongo();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveClicksLogTest() throws IllegalArgumentException,
			IllegalAccessException {
		Document document = ConvertUtil.convertDoc(this.initClicks());
		mongoClient.getDatabase("ClicksLogTest").getCollection("clicksLog")
				.insertOne(document);
	}

	@Test
	public void queryClicksLogTest() {
		long count = mongoClient.getDatabase("ClicksLogTest")
				.getCollection("clicksLog").count(new Document("clickPosition", "p_001"));
		System.out.println(count);
	}

	@After
	public void closeMongoClient() {
		mongoClient.close();
	}

	/**
	 * @描述 : 初始化点击日志信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日上午11:56:36
	 *
	 * @return
	 */
	private ClicksLog initClicks() {
		ClicksLog clicksLog = new ClicksLog();
		clicksLog.setClickPosition("p_001");
		clicksLog.setPageCode("page_001");
		clicksLog.setPageContent("广告页面");
		clicksLog.setUrl("http://test.ad.com");
		return clicksLog;
	}

}
