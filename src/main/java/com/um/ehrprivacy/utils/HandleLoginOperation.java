package com.um.ehrprivacy.utils;

import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.um.ehrprivacy.dao.ConnectionDB;

/**
 *  Handle doctor or patient login verify operation.
 *  
 * @author peterliu
 *
 */
public class HandleLoginOperation {
	
	/**
	 *  Verify user's name and password is correct or not.
	 *  	Doctor user login.
	 *  
	 * @param doctorid
	 * @param password
	 * @return
	 */
	public static boolean verifyLegitimacyOfUser(String doctorid, String password){
		if(doctorid.equals("") || password.equals("")){
			return false;
		}
		// 1. Based on user name( doctor id), query node information of doctor from Doctor Index Table.
		String doctorIndexHost = "";
		int doctorIndexPort = 27017;
		String doctorIndexDatabase = "";
		String doctorIndexCollection = "";
		// 2. Get node information.
		Map<String, String> nodeinfo = ConnectionDB.getNodeInforOfDoctor(doctorIndexHost, doctorIndexPort, doctorIndexDatabase, doctorIndexCollection, doctorid);
		
		if( nodeinfo == null ){
			return false;
		}
		
		// 2. According to the node information, query that node to verify password.
		
		String doctorNodeHost = nodeinfo.get("host");
		int doctorNodePort = Integer.valueOf(nodeinfo.get("port"));
		String doctorNodeDatabase = nodeinfo.get("database");
		String doctorNodeCollection = nodeinfo.get("collection");
		
		// 3. Return verify result.
		return ConnectionDB.verifyDoctorLoginInfo(doctorNodeHost, doctorNodePort, doctorNodeDatabase, doctorNodeCollection, doctorid, password);
	}
}
