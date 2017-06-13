package com.book.demo.mongo.app.mongo;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * @描述 : mongodb工具类
 * @创建者：liushengsong
 * @创建时间： 2017年2月20日下午3:00:44
 *
 */
public class MongoDBUtil {

	/**
	 * @描述 : 初始化MongoClient
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日下午3:01:43
	 *
	 * @return
	 * @throws IOException
	 */
	public static MongoClient initMongo() throws IOException {
		// 加载mongo配置文件
		InputStream inputStream = MongoDBUtil.class.getClass()
				.getResourceAsStream("/mongo-config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		WriteConcern concern = new WriteConcern(Integer.valueOf(properties
				.getProperty("write")), Integer.valueOf(properties
				.getProperty("writeTimeout")));
		concern.withJournal(Boolean.valueOf(properties.getProperty("journal")));
		Builder builder = MongoClientOptions
				.builder()
				.connectionsPerHost(
						Integer.valueOf(properties
								.getProperty("connectionsPerHost")))
				.connectTimeout(
						Integer.valueOf(properties
								.getProperty("connectTimeout")))
				.cursorFinalizerEnabled(
						Boolean.valueOf(properties
								.getProperty("cursorFinalizerEnabled")))
				.maxWaitTime(
						Integer.valueOf(properties.getProperty("maxWaitTime")))
				.threadsAllowedToBlockForConnectionMultiplier(
						Integer.valueOf(properties
								.getProperty("threadsAllowedToBlockForConnectionMultiplier")))
				.socketTimeout(
						Integer.valueOf(properties.getProperty("socketTimeout")))
				.socketKeepAlive(
						Boolean.valueOf(properties
								.getProperty("socketKeepAlive")))
				.writeConcern(concern);
		if (Boolean.valueOf(properties.getProperty("readSecondary"))) {
			builder.readPreference(ReadPreference.secondaryPreferred());
		}
		String[] address = properties.getProperty("hostConfString").split(":");
		ServerAddress serverAddress = new ServerAddress(address[0],
				Integer.valueOf(address[1]));
		return new MongoClient(serverAddress, builder.build());
	}
}
