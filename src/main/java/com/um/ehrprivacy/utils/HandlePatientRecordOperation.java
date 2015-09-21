package com.um.ehrprivacy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.um.ehrprivacy.model.PatientInfo;
import com.um.ehrprivacy.model.PatientRecord;

/**
 *  Patient records operation.
 *  	
 *  	main process:
 *  		1. Get the node information of patient record from Patient Index Table.
 *  		2. Search records from those nodes.		
 *  
 * @author peterliu
 *
 */
public class HandlePatientRecordOperation {
	
	/**
	 *  Get all node informations of patient from Patient Index Table.
	 *  Each patient may be have many nodes.
	 * @param patientid
	 * @return
	 */
	public static List<HashMap<String, String>> getPatientNodeInfoList(String patientid){
		if(patientid.equals("")){
			return null;
		}
		final List<HashMap<String, String>> nodeList = new ArrayList<HashMap<String,String>>();
		
		// The patient index table node information.
		String patientIndexHost = "10.119.180.42";
		int patientIndexPort = 27017;
		String patientIndexDatabase = "EhrPrivacy";
		String patientIndexCollection = "PatientIndex";
		
		// Get the database connection.
		MongoClient client = new MongoClient(patientIndexHost , patientIndexPort);	
				
		try {
			
			MongoDatabase database = client.getDatabase(patientIndexDatabase);
	        
			MongoCollection<Document> resultCollection = database.getCollection(patientIndexCollection);
				        
			// Query the collection to get doctor information.
			FindIterable<Document> iterable = resultCollection.find(new Document("patient.Patient_ID", patientid));
			
			iterable.forEach(new Block<Document>() {

				@Override
				public void apply(Document document) {
					// TODO Auto-generated method stub
					HashMap<String, String> patientNodeMap = new HashMap<String, String>();
					
					Document doc = (Document) document.get("patient");
					
					patientNodeMap.put("patientid", doc.getString("Patient_ID"));
					patientNodeMap.put("host", doc.getString("host"));
					patientNodeMap.put("port", doc.getString("port"));
					patientNodeMap.put("database", doc.getString("database"));
					patientNodeMap.put("collection", doc.getString("collection")); // Patient informations
					nodeList.add(patientNodeMap);
				}
			});
			
		} finally {
			// TODO: handle finally clause
			client.close();
		}
		
		return nodeList;
	}
	
	/**
	 *   Get all patient records 
	 *   
	 * @param patientNodeInfos
	 * @param userid --- privacy ,control which informations can show to doctor.
	 * @return
	 */
	public static List<PatientRecord> getPatientRecords(List<HashMap<String, String>> patientNodeInfos, String patientid , String userid){
		if(patientNodeInfos == null || patientNodeInfos.size() == 0 || userid.equals("")){
			return null;
		}
		final List<PatientRecord> patientRecords = new ArrayList<PatientRecord>();
		
		// 1. Based on the nodes informations, query all nodes.
		int nodeLength = patientNodeInfos.size();
		
		for(int i = 0; i < nodeLength; i++){
			// Node basic info
			String nodeHost = patientNodeInfos.get(i).get("host");
			int nodePort = Integer.valueOf(patientNodeInfos.get(i).get("port"));
			String nodeDB = patientNodeInfos.get(i).get("database");
			String nodeCollection = patientNodeInfos.get(i).get("collection");
			
			MongoClient client = new MongoClient(nodeHost, nodePort);
			
			MongoDatabase db = client.getDatabase(nodeDB);
			
			MongoCollection<Document> collection = db.getCollection(nodeCollection);
			
			// Find document------
			FindIterable<Document> iterable = collection.find(new Document("patientRecord.IDCardNO", patientid));
			
			iterable.forEach(new Block<Document>() {

				@Override
				public void apply(Document document) {
					// TODO Auto-generated method stub
					// Each document of patient record need to convert to PatientRecord.
					
					// Convert method!!!!-----   Document --> PatientRecord
					PatientRecord patientRecord = convertDocumentToPatientRecord(document);
					
					patientRecords.add(patientRecord);
				}
			});
			
			client.close();
		}
		
		// Return results
		return patientRecords;
	}
	
	/**
	 * Get the patient informations.
	 * 
	 * @param patientid
	 * @return
	 */
//	public static PatientInfo getPatientInfo(String patientid){
//		
//	}
	
	/**
	 *  Convert methods ----:  Document - > PatientRecord
	 * @param document
	 * @return
	 */
	public static PatientRecord convertDocumentToPatientRecord(Document document){
		if(document == null){
			return null;
		}
		PatientRecord patientRecord = new PatientRecord();
		
		//Set attributes
		
		
		return patientRecord;
	}
	
}
