package com.book.demo.goods.test;

import java.io.IOException;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.goods.model.Goods;
import com.book.demo.goods.mongo.MongoDBUtil;
import com.book.demo.goods.util.ConvertUtil;
import com.mongodb.MongoClient;

public class MongoDBTest {

	private MongoClient mongoClient;

	@Before
	// 注解before 表示在方法前执行
	public void initMongoClient() throws IOException {
		mongoClient = MongoDBUtil.initMongo();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveGoodsTest() throws IllegalArgumentException,
			IllegalAccessException {
		Document document = ConvertUtil.convertDoc(this.initGoods());
		mongoClient.getDatabase("GoodsTest").getCollection("goods")
				.insertOne(document);
	}

	@Test
	public void queryGoodsTest() {
		Document doc = mongoClient.getDatabase("GoodsTest")
				.getCollection("goods").findOneAndUpdate(new Document("id", 4380878), new Document("adInfo", "<htt><htt>"));
		System.out.println(doc.toJson());
	}

	@After
	public void closeMongoClient() {
		mongoClient.close();
	}

	/**
	 * @描述 : 初始化商品信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日上午11:56:36
	 *
	 * @return
	 */
	private Goods initGoods() {
		Goods goods = new Goods();
		goods.setAdInfo("<html></html>");
		goods.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品产地：中国大陆");
		goods.setId(4380878);
		goods.setSpecificationsInfo("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统操作系统Windows 10家庭版处理器CPU类型Intel 第7代 酷睿CPU速度2.5GHz三级缓存6M其它说明I5-7300HQ芯片组芯片组其它　");
		return goods;
	}

}
