package com.book.demo.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class HelloWorld {

	public static void main(String[] args) {
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		MongoCollection<Document> collection = client.getDatabase("test")
				.getCollection("test");
		collection.find();
		collection.insertOne(new Document().append("name", "zhangsan"));
		collection.updateOne(Filters.eq("name", "zhangsan"),
				new Document().append("name", "lisi"));
		collection.deleteMany(Filters.eq("name", "lisi"));
		client.close();
	}
	public void test(){
		MongoClient client = new MongoClient("127.0.0.1", 27017);
		MongoCollection<Document> collection = client.getDatabase("test")
				.getCollection("test");
		collection.createIndex(new Document());
		collection.aggregate((List<? extends Bson>) new ArrayList<Bson>());
		collection.dropIndex("test");
		client.close();
	}
}
