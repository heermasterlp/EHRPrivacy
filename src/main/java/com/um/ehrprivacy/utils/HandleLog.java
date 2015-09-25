package com.um.ehrprivacy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.print.Doc;

import org.bson.Document;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.um.ehrprivacy.model.TrackerLog;

public class HandleLog {
	
	/**
	 *  
	 * @param logger
	 */
	public static void recordLog(Map<String, String> logmap){
		if(logmap == null || logmap.size() == 0){
			return;
		}
		
		// The patient index table node information.
		String trackerHost = "10.119.180.42";
		int trackerPort = 27017;
		String trackerDatabase = "Log";
		String trackerCollection = "Log";
				
		// Get the database connection.
		MongoClient client = new MongoClient(trackerHost , trackerPort);	
						
		try {
					
			MongoDatabase database = client.getDatabase(trackerDatabase);
			        
			MongoCollection<Document> resultCollection = database.getCollection(trackerCollection);
						        
			// Insert the log record
			resultCollection.insertOne(new Document().append("UserID", logmap.get("UserID"))
					.append("UserType", logmap.get("UserType"))
					.append("IPAddress", logmap.get("IPAddress"))
					.append("Operation", logmap.get("Operation"))
					.append("TimeStamp", logmap.get("TimeStamp"))
					.append("Location", logmap.get("Location"))
					.append("EHRID", logmap.get("EHRID"))
					.append("EHRType", logmap.get("EHRType")));
					
			} finally {
					// TODO: handle finally clause
				client.close();
			}
	}
	
	
	/**
	 *  
	 * @param userid
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<TrackerLog> getTrackerLogList(String ehrid, String startDate, String endDate){
		if(ehrid.equals("")){
			return null;
		}
		final List<TrackerLog> list = new ArrayList<TrackerLog>();
		
		String trackerHost = "10.119.180.42";
		int trackerPort = 27017;
		String trackerDatabase = "Log";
		String trackerCollection = "Log";
				
		// Get the database connection.
		MongoClient client = new MongoClient(trackerHost , trackerPort);	
						
		try {
					
			MongoDatabase database = client.getDatabase(trackerDatabase);
			        
			MongoCollection<Document> resultCollection = database.getCollection(trackerCollection);
						        
			// Insert the log record
			Document conditions = new Document("EHRID",ehrid);
			if(!startDate.equals("") && !endDate.equals("")){
				conditions.append("TimeStamp", new Document("$gt",startDate).append("$lte", endDate));
			}
			
			FindIterable<Document> iterable = resultCollection.find(conditions);
			
			iterable.forEach(new Block<Document>() {

				@Override
				public void apply(Document document) {
					// TODO Auto-generated method stub
					TrackerLog trackerLog = convertDocumentToTrakcerLog(document);
					
					list.add(trackerLog);
				}
				
			});
			
					
			} finally {
					// TODO: handle finally clause
				client.close();
			}
		
		return list;
	}
	
	/**
	 * 
	 * @param document
	 * @return
	 */
	public static TrackerLog convertDocumentToTrakcerLog(Document document){
		if(document == null){
			return null;
		}
		
		TrackerLog trackerLog = new TrackerLog();
		
		trackerLog.setUserID(document.getString("UserID"));
		trackerLog.setUserType(document.getString("UserType"));
		trackerLog.setIPAddress(document.getString("IPAddress"));
		trackerLog.setOperation(document.getString("Operation"));
		trackerLog.setTimeStamp(document.getString("TimeStamp"));
		trackerLog.setLocation(document.getString("Location"));
		trackerLog.setEHRID(document.getString("EHRID"));
		trackerLog.setEHRType(document.getString("EHRType"));
		
		return trackerLog;
	}
}
