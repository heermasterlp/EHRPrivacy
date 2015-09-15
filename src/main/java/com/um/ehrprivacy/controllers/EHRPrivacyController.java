package com.um.ehrprivacy.controllers;

import java.util.ArrayList;
import java.util.List;
import com.um.ehrprivacy.model.PatientRecord;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EHRPrivacyController {
	
	/* Query the records based on the conditions */
	@RequestMapping(value = "queryrecord", method = RequestMethod.POST)
	public String handleQuery( HttpServletRequest request, Model model){
			
		List<PatientRecord> result = generateRecords();
		System.out.println("Get the request !");
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
		
		
		return jsonString;
	}
	
}
