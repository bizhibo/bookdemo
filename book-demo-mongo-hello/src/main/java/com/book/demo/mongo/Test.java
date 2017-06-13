package com.book.demo.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class Test {

	private static MongoClient client;

	public static void main(String[] args) {
		client = new MongoClient("127.0.0.1", 27017);
		MongoCollection<Document> collection = client.getDatabase("test")
				.getCollection("test");
		MongoTest t1 = new MongoTest(collection);
		MongoTest t2 = new MongoTest(collection);
		MongoTest t3 = new MongoTest(collection);
		t1.run();
		t2.run();
		t3.run();
	}
}
