package com.um.ehrprivacy.utils;

import com.google.gson.Gson;
import com.um.ehrprivacy.model.PatientRecord;

public class ObjectToJson {
	
	
	public String objectToJson(PatientRecord patientRecord){
		Gson gson = new Gson();
		String json = gson.toJson(patientRecord);
		System.out.println(json);
		return json;
	}
}
