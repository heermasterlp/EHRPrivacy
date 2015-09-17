package com.um.ehrprivacy.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.um.ehrprivacy.model.PatientRecord;
import com.um.ehrprivacy.utils.HandlePatientRecordOperation;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EHRPrivacyController {
	
	/* Query the records based on the conditions */
	@RequestMapping(value = "queryrecord", method = RequestMethod.POST)
	public String handleQuery( HttpServletRequest request, Model model, HttpSession session){
			
		List<PatientRecord> result = generateRecords();
		System.out.println("Get the request !");
		System.out.println("userid:" + session.getAttribute("userId"));
		
		
		// 1. Get the query conditions: patient id , date, hospital id
		String patientid = request.getParameter("patientid");
		String userid = (String) session.getAttribute("userId"); // userid privacy method
		
		// 2. Search patient node information in the Patient Index collections.
		List<HashMap<String, String>> patientNodeInfos = HandlePatientRecordOperation.getPatientNodeInfoList(patientid);
		
		// 3. Request the patient records to the nodes got from the Patient Index collections.
		result = HandlePatientRecordOperation.getPatientRecords(null, patientid, userid);
		
		// 4. Do the whole query operations in all nodes.
		// 5. Format the query results and return.
		
		model.addAttribute("ehealthrecrods", result);
		return "success";
	}
	
	
	/**
	 *  Generate the patient record data
	 * @return
	 */
	public List<PatientRecord> generateRecords(){
		List<PatientRecord> list = new ArrayList<PatientRecord>();
		
		PatientRecord p = null;
		for(int i = 0; i < 10; i++ ){
			p = new PatientRecord();
			
			p.setRecordID(i + "");
			p.setHospitalID(i + "");
			p.setExamination(i + "");
			p.setDiagnose(i + "");
			p.setDoctor(i + "");
			
			list.add(p);
		}
		
		return list;
	}
	
	/**
	 *  Convert the list to json.
	 *  
	 * @param list
	 * @return
	 */
	public String converListToJSON(List<PatientRecord> list) {
		if(list == null || list.size() == 0){
			return "";
		}
		String jsonString = "";
		
		jsonString = new Gson().toJson(list);
		
		return jsonString;
	}
	
}
