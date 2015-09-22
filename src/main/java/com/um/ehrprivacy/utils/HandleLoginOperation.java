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
}
