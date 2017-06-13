package com.book.demo.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoTest implements Runnable {

	private MongoCollection<Document> collection;

	public MongoTest(MongoCollection<Document> collection){
		this.collection = collection;
	}
	
	private List<Document> init() {
		List<Document> list = new ArrayList<Document>(1000);
		for (int i = 0; i < 1000; i++) {
			Document doc = new Document("test", i);
			list.add(doc);
		}
		return list;
	}

	public void run() {
		collection.insertMany(this.init());
	}

}
