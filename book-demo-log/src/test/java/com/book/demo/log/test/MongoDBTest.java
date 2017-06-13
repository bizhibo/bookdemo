package com.book.demo.log.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.log.model.Log;
import com.book.demo.log.mongo.MongoDBUtil;
import com.book.demo.log.util.ConvertUtil;
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
	public void saveLogTest() throws IllegalArgumentException,
			IllegalAccessException {
		mongoClient.getDatabase("LogTest").getCollection("log")
				.insertOne(ConvertUtil.convertDoc(this.initLog()));
	}

	@Test
	public void queryLogTest() {
		FindIterable<Document> iter = mongoClient.getDatabase("LogTest")
				.getCollection("log").find();
		iter.forEach(new Block<Document>() {
			public void apply(Document doc) {
				System.out.println(doc.toJson());
			}
		});
	}

	@After
	public void closeMongoClient() {
		mongoClient.close();
	}

	/**
	 * @描述 : 初始化日志数据
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月18日下午9:04:53
	 *
	 * @return
	 */
	private Log initLog() {
		Exception e = new NullPointerException("-------Test-------");
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		Log log = new Log();
		log.setCreatedTime(new Date());
		log.setLevel("ERROR");
		log.setMessage(e.getMessage());
		log.setStackTrace(sw.getBuffer().toString());
		return log;
	}

}
