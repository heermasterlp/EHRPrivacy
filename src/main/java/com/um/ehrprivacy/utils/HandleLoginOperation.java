package com.um.ehrprivacy.utils;

import java.util.Map;

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
		
		String doctorNodeHost = "10.119.180.43";
		int doctorNodePort = 27017;
		
		String doctorNodeDatabase = "EhrPrivacy";
		String doctorNodeCollection = "Doctor";
		
		// 3. Return verify result.
		return ConnectionDB.verifyDoctorLoginInfo(doctorNodeHost, doctorNodePort, doctorNodeDatabase, doctorNodeCollection, doctorid, password);
	}
	
	/**
	 *  
	 * @param doctorid
	 * @param password
	 * @return
	 */
	public static boolean verifyLegitimacyOfTracker(String trackerid, String password){
		if(trackerid.equals("") || password.equals("")){
			return false;
		}
		
		String trackerNodeHost = "10.119.180.42";
		int trackerNodePort = 27017;
		
		String trackerNodeDatabase = "Log";
		String trackerNodeCollection = "Trackers";
		
		// 3. Return verify result.
		return ConnectionDB.verifyDoctorLoginInfo(trackerNodeHost, trackerNodePort, trackerNodeDatabase, trackerNodeCollection, trackerid, password);
	}
}
