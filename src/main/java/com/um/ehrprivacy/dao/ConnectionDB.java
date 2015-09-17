package com.um.ehrprivacy.dao;

import java.util.HashMap;
import java.util.Map;

import javax.print.Doc;
import javax.security.auth.callback.Callback;

import org.bson.Document;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
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
        
        client.close();
        
        return resultCollection;
	}
	
	/**
	 *  Get all document of collection based on the : host, port, database and collection.
	 *  
	 * @param host
	 * @param port
	 * @param db
	 * @param collection
	 * @return
	 */
	public static MongoCollection<Document> getCollection(String host, int port, String db, String collection){
		
		MongoClient client = new MongoClient(host , port);		
		
        MongoDatabase database = client.getDatabase(db);
        
        MongoCollection<Document> resultCollection = database.getCollection(collection);
        
        client.close(); //í
        
        return resultCollection;
	}
	
	/**
	 *  Get doctor node information from doctor index table.
	 * @param host
	 * @param port
	 * @param db
	 * @param collection
	 * @param doctorid
	 * @return
	 */
	public static Map<String, String> getNodeInforOfDoctor(String host, int port, String db, String collection, final String doctorid){
		
		final Map<String, String> nodeinformation = new HashMap<String, String>();
		
		// Get the database connection.
		MongoClient client = new MongoClient(host , port);	
		
		try {
			
	        MongoDatabase database = client.getDatabase(db);
	        
	        MongoCollection<Document> resultCollection = database.getCollection(collection);
	        
	        // Query the collection to get doctor information.
	        FindIterable<Document> iterable = resultCollection.find(new Document("doctorid",doctorid));
	        
		    // Get the node information document.
	        Document nodeinfo = iterable.first();
	        
	        nodeinformation.put("host", nodeinfo.getString("host"));
			nodeinformation.put("port", nodeinfo.getString("port"));
			nodeinformation.put("database", nodeinfo.getString("document"));
			nodeinformation.put("collection", nodeinfo.getString("collection"));
			nodeinformation.put("doctorid", doctorid);
	        
	        // Clear client.
			return nodeinformation;
			
		} finally {
			if( client != null){
				client.close(); 
			}
		}
	}
	
	/**
	 * Verify login user is legal.
	 * 
	 * @param host
	 * @param port
	 * @param db
	 * @param collection
	 * @param password
	 * @return
	 */
	public static boolean verifyDoctorLoginInfo(String host, int port, String db, String collection, String doctorid, String password){
		if(password.equals("")){
			return false;
		}
		
		// Query that node to get doctol legal password.
		MongoClient client = new MongoClient(host , port);
		
		try {
			MongoDatabase database = client.getDatabase(db);
	        
	        MongoCollection<Document> resultCollection = database.getCollection(collection);
	        
	        // Query the collection to get doctor information.
	        FindIterable<Document> iterable = resultCollection.find(new Document("doctorid", doctorid));
	        
	        // Get doctor info.
	        Document doctorinfo = iterable.first();
	        if( !password.equals(doctorinfo.getString("password"))){
	        	
	        	return false;
	        }
			return true;
			
		} finally {
			// TODO: handle finally clause
			if(client != null){
				client.close();
			}
		}
	}
	
	
	
	
	
}
