package com.book.demo.history.order.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.history.order.model.Goods;
import com.book.demo.history.order.model.HistoryOrder;
import com.book.demo.history.order.mongo.MongoDBUtil;
import com.book.demo.history.order.util.ConvertUtil;
import com.book.demo.history.order.util.JsonUtil;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

public class MongoDBTest {

	private MongoClient mongoClient;

	@Before
	// 注解before 表示在方法前执行
	public void initMongoClient() throws IOException {
		mongoClient = MongoDBUtil.initMongo();
	}

	@Test(timeout = 1000)
	// timeout表示该测试方法执行超过1s会抛出异常
	public void saveHistoryOrderTest() throws IllegalArgumentException,
			IllegalAccessException {
		List<Document> documents = ConvertUtil.convertDoc(this
				.initHistoryOrders());
		mongoClient.getDatabase("HistoryOrderTest")
				.getCollection("historyOrder").insertMany(documents);
	}

	@Test
	public void queryHistoryOrderTest() {
		FindIterable<Document> iter = mongoClient
				.getDatabase("HistoryOrderTest").getCollection("historyOrder")
				.find(Filters.text("1")).limit(3);
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
	 * @描述 : 初始化历史订单信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日上午11:56:36
	 *
	 * @return
	 */
	private List<HistoryOrder> initHistoryOrders() {
		List<HistoryOrder> historyOrders = new ArrayList<HistoryOrder>();
		Goods goods = new Goods();
		goods.setAdInfo("<html></html>");
		goods.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品产地：中国大陆");
		goods.setId(4380878);
		goods.setSpecificationsInfo("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统操作系统Windows 10家庭版处理器CPU类型Intel 第7代 酷睿CPU速度2.5GHz三级缓存6M其它说明I5-7300HQ芯片组芯片组其它　");
		HistoryOrder historyOrder1 = new HistoryOrder();
		historyOrder1.setCreatedDate(new Date());
		historyOrder1.setGoodsList(JsonUtil.toJson(goods));
		historyOrder1.setOrderId(3456712);
		historyOrder1.setUserName("zhangsan");
		HistoryOrder historyOrder2 = new HistoryOrder();
		historyOrder2.setCreatedDate(new Date());
		historyOrder2.setGoodsList(JsonUtil.toJson(goods));
		historyOrder2.setOrderId(3456712);
		historyOrder2.setUserName("zhangsan");
		historyOrders.add(historyOrder1);
		historyOrders.add(historyOrder2);
		return historyOrders;
	}

}
