package com.um.ehrprivacy.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionDB {
	
	/**
	 *  数据库连接
	 */
	
	public static MongoCollection<Document> getCollection(String db,String collection){
		
		MongoClient client = new MongoClient("localhost",27017);		
	
        MongoDatabase database = client.getDatabase(db);
        
        MongoCollection<Document> resultCollection = database.getCollection(collection);
        
        System.out.println("success: " + resultCollection.count());
        
        return resultCollection;
	}
}
