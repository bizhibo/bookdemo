package com.book.demo.comment.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.book.demo.comment.model.Comment;
import com.book.demo.comment.model.CommentLabel;
import com.book.demo.comment.mongo.MongoDBUtil;
import com.book.demo.comment.util.ConvertUtil;
import com.book.demo.comment.util.JsonUtil;
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
	public void saveCommentTest() throws IllegalArgumentException,
			IllegalAccessException {
		Document document = ConvertUtil.convertDoc(this.initComment());
		mongoClient.getDatabase("CommentTest").getCollection("comment")
				.insertOne(document);
	}

	@Test
	public void queryCommentTest() {
		FindIterable<Document> iter = mongoClient.getDatabase("CommentTest")
				.getCollection("comment").find().skip(2).limit(2);
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
	 * @描述 : 初始化评价信息
	 * @创建者：liushengsong
	 * @创建时间： 2017年2月20日上午11:56:36
	 *
	 * @return
	 */
	private Comment initComment() {
		List<CommentLabel> commentLabels = new ArrayList<CommentLabel>();
		CommentLabel commentLabel1 = new CommentLabel();
		commentLabel1.setContent("商品不错");
		CommentLabel commentLabel2 = new CommentLabel();
		commentLabel2.setContent("非常耐用");
		commentLabels.add(commentLabel1);
		commentLabels.add(commentLabel2);
		Comment comment = new Comment();
		comment.setCommentLabels(JsonUtil.toJson(commentLabels));
		comment.setContent("快递非常快，下次还会买。");
		comment.setCreatedTime(new Date());
		comment.setPid(9000198);
		comment.setStar(4);
		comment.setUserName("admin");
		return comment;
	}

}
