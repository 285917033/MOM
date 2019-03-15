package com.wbd.mongodb.crud.jbs;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

/**
 * mongodb crud
 * 
 * @author jwh
 *
 */
public class JbsMongodbCRUD {

	private final static String DB_NAME = "zgh";
	private final static String COLLECTION_NAME = "scores";

	public static void main(String[] args) {
		// 1.创建MongoClient对象

		MongoClient mongoClient = new MongoClient("192.168.1.82", 27017);

		// 2.获取db对象

		MongoDatabase md = mongoClient.getDatabase(DB_NAME);

		// 3.获取collection对象
		MongoCollection collection = md.getCollection(COLLECTION_NAME);

	

		
		// add
		Document d = new Document();
		d.put("studentid", "s1");
		d.put("course", "课程五");
		// collection.insertOne(d);

		//总条数
		System.out.println("总条数" + collection.count());

		// 删除
		//DeleteResult dr = collection.deleteOne(BasicDBObject.parse("{'course':'课程5'}"));
		
		//System.out.println(dr.getDeletedCount());

		Bson filter = Filters.eq("score", 10.944233279065452);
		Document ds =  new Document("$set",new Document("course","英语"));
		//更新  
		UpdateResult ur2 =collection.updateOne(filter,ds);
	
		System.out.println(ur2.getMatchedCount()+"更新成功");
		
		FindIterable fi = collection.find().limit(10);

		MongoCursor cursor = fi.iterator();
		//查询所有
		while (cursor.hasNext()) {

			System.out.println(cursor.next());
		}
		
		FindIterable fi2 =collection.find(Filters.eq("course", "英语"));

		System.out.println(fi2.first());
		
	}

}
