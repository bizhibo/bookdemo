package com.book.demo.user.test;

import java.io.IOException;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.user.model.UserExtendInfo;
import com.book.demo.user.model.UserInfo;
import com.book.demo.user.mongo.MongoDBUtil;
import com.book.demo.user.util.ConvertUtil;
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
	public void saveUserTest() throws IllegalArgumentException,
			IllegalAccessException {
		Document userInfoDoc = ConvertUtil.convertDoc(this.initUserInfo());
		Document userBaseDoc = ConvertUtil.convertDoc(
				this.initUserExtendInfo(), userInfoDoc);
		mongoClient.getDatabase("UserTest").getCollection("user")
				.insertOne(userBaseDoc);
	}

	@Test
	public void queryUserTest() {
		FindIterable<Document> findIterable = mongoClient
				.getDatabase("UserTest").getCollection("user")
				.find(new Document("age", 20).append("name", "张三"));
		System.out.println(findIterable.first().toJson());
	}

	@After
	public void closeMongoClient() {
		mongoClient.close();
	}

	/**
	 * @描述 : 初始化用户信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日下午3:07:44
	 *
	 * @return
	 */
	private UserInfo initUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAge(20);
		userInfo.setName("张三");
		userInfo.setNickName("小明");
		userInfo.setSex("男");
		userInfo.setUserName("zhangsan");
		return userInfo;
	}

	/**
	 * @描述 : 初始化用户扩展信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日下午3:07:56
	 *
	 * @return
	 */
	private UserExtendInfo initUserExtendInfo() {
		UserExtendInfo userExtendInfo = new UserExtendInfo();
		userExtendInfo.setAddress("中国北京");
		userExtendInfo.setCodeNum("123456789012345678");
		userExtendInfo.setPhone("13000012121");
		userExtendInfo.setSchool("清华大学");
		return userExtendInfo;
	}

}
