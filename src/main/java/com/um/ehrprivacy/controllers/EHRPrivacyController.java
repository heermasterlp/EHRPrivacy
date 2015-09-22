package com.um.ehrprivacy.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.um.ehrprivacy.model.PatientRecord;
import com.um.ehrprivacy.utils.HandlePatientRecordOperation;
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
		
		// 1. Get the query conditions: patient id , date, hospital id
		String patientid = request.getParameter("patientid");
		String userid = (String) session.getAttribute("userId"); // userid privacy method
		
		String startDate = request.getParameter("querystartdate");
		
		String endDate   = request.getParameter("queryenddate");
		
		String hospitalID = request.getParameter("hospitalid");
		
		// 2. Search patient node information in the Patient Index collections.
		List<HashMap<String, String>> patientNodeInfos = HandlePatientRecordOperation.getPatientNodeRecordList(patientid);
		
		Map<String, String> infoNodes = HandlePatientRecordOperation.getPatientNodeInfoMap(patientid); // Patient informations node 
		
		// 3. Request the patient records to the nodes got from the Patient Index collections.
		List<PatientRecord> result = HandlePatientRecordOperation.getPatientRecords(patientNodeInfos, infoNodes ,patientid, userid, startDate, endDate, hospitalID);
		
		// 4. Format the query results and return.
		model.addAttribute("username", userid);
		model.addAttribute("ehealthrecrods", result);
		return "success";
	}
	
	/**
	 *  Get detail of records.
	 * @param patientid
	 * @param recordid
	 * @param hospitalid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public String handleDetail(String patientid,String recordid,String hospitalid, Model model, HttpSession session){
		
		String userid = (String) session.getAttribute("userId");
		// 2. Search patient node information in the Patient Index collections.
		List<HashMap<String, String>> patientNodeInfos = HandlePatientRecordOperation.getPatientNodeRecordList(patientid);
		Map<String, String> infoNodes = HandlePatientRecordOperation.getPatientNodeInfoMap(patientid);
		
		// 3. Request the patient records to the nodes got from the Patient Index collections.
		List<PatientRecord> result = HandlePatientRecordOperation.getPatientRecords(patientNodeInfos, infoNodes ,patientid, userid, "", "", hospitalid);
		
		for(PatientRecord p : result){
			if(recordid.trim().equals(p.getPatientRecordID().trim())){
				model.addAttribute("record", p);
			}
		}
		model.addAttribute("username", userid);
		return "detail";
	}
	
}
