package com.um.ehrprivacy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.Doc;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.um.ehrprivacy.model.HBVaccineInjectionRecordSet;
import com.um.ehrprivacy.model.HemodialysisChartRecordSet;
import com.um.ehrprivacy.model.HemodialysisRecordSet;
import com.um.ehrprivacy.model.LaboratoryReportRecordSet;
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
		
		Document record = (Document) document.get("patientRecord");
		if(record == null){
			return patientRecord;
		}
		
		String HospitalID = record.getString("HospitalID");
		String IDCardNO = record.getString("IDCardNO");
		String Date = record.getString("Date");
		
		patientRecord.setHospitalID(HospitalID);
		patientRecord.setIDCardNO(IDCardNO);
		patientRecord.setDate(Date);
		
		HemodialysisChartRecordSet hemodialysisChartRecordSet = new HemodialysisChartRecordSet();
		Document hcrDocument = (Document) record.get("HemodialysisChartRecordSet");
		
		hemodialysisChartRecordSet.setBloodFlow(hcrDocument.getString("BloodFlow"));
		hemodialysisChartRecordSet.setDiastolicPressure(hcrDocument.getString("DiastolicPressure"));
		hemodialysisChartRecordSet.setNa(hcrDocument.getString("Na"));
		hemodialysisChartRecordSet.setPulse(hcrDocument.getString("Pulse"));
		hemodialysisChartRecordSet.setSystolicPressure(hcrDocument.getString("SystolicPressure"));
		hemodialysisChartRecordSet.setTemp(hcrDocument.getString("Temp"));
		hemodialysisChartRecordSet.setTime(hcrDocument.getString("Time"));
		hemodialysisChartRecordSet.setTMP(hcrDocument.getString("TMP"));
		hemodialysisChartRecordSet.setUF(hcrDocument.getString("UF"));
		hemodialysisChartRecordSet.setVen(hcrDocument.getString("Ven"));
		patientRecord.setHemodialysisChartRecordSet(hemodialysisChartRecordSet);
		
		HemodialysisRecordSet hRecordSet = new HemodialysisRecordSet();
		Document hrsDocument = (Document) record.get("HemodialysisRecordSet");
		hRecordSet.setAntiCoagulation(hrsDocument.getString("AntiCoagulation"));
		hRecordSet.setAntiCoDosage(hrsDocument.getString("AntiCoDosage"));
		hRecordSet.setArhythmia(hrsDocument.getString("Arhythmia"));
		hRecordSet.setBloodAccess(hrsDocument.getString("BloodAccess"));
		hRecordSet.setCheckNurse(hrsDocument.getString("CheckNurse"));
		hRecordSet.setCloseNurse(hrsDocument.getString("CloseNurse"));
		hRecordSet.setComments(hrsDocument.getString("Comments"));
		hRecordSet.setCramp(hrsDocument.getString("Cramp"));
		hRecordSet.setD3ZemplarDosage(hrsDocument.getString("D3ZemplarDosage"));
		hRecordSet.setDarbepoetinDosage(hrsDocument.getString("DarbepoetinDosage"));
		hRecordSet.setDate(hrsDocument.getString("Date"));
		hRecordSet.setDialysisBlockage(hrsDocument.getString("DialysisBlockage"));
		hRecordSet.setDialyzer(hrsDocument.getString("Dialyzer"));
		hRecordSet.setDoctor(hrsDocument.getString("Doctor"));
		hRecordSet.setDryWeight(hrsDocument.getString("DryWeight"));
		hRecordSet.setDurationH(hrsDocument.getString("DurationH"));
		hRecordSet.setDurationM(hrsDocument.getString("DurationM"));
		hRecordSet.setGlucoseDosage(hrsDocument.getString("GlucoseDosage"));
		hRecordSet.setHypertension(hrsDocument.getString("Hypertension"));
		hRecordSet.setHypotension(hrsDocument.getString("Hypotension"));
		hRecordSet.setKCI(hrsDocument.getString("KCI"));
		hRecordSet.setMachine(hrsDocument.getString("Machine"));
		hRecordSet.setNetLoss(hrsDocument.getString("NetLoss"));
		hRecordSet.setNSDosage(hrsDocument.getString("NSDosage"));
		hRecordSet.setNSFlushVolume(hrsDocument.getString("NSFlushVolume"));
		hRecordSet.setProgramme(hrsDocument.getString("Programme"));
		hRecordSet.setRecommonDosage(hrsDocument.getString("RecommonDosage"));
		hRecordSet.setStartNurse(hrsDocument.getString("StartNurse"));
		hRecordSet.setTargetLoss(hrsDocument.getString("TargetLoss"));
		hRecordSet.setTimes(hrsDocument.getString("Times"));
		hRecordSet.setVenoferDosage(hrsDocument.getString("VenoferDosage"));
		hRecordSet.setVomiting(hrsDocument.getString("Vomiting"));
		hRecordSet.setWeightPostD(hrsDocument.getString("WeightPostD"));
		hRecordSet.setWeightPreD(hrsDocument.getString("WeightPreD"));
		hRecordSet.setWithdrawalNurse(hrsDocument.getString("WithdrawalNurse"));
		patientRecord.setHemodialysisRecordSet(hRecordSet);
		
		HBVaccineInjectionRecordSet hbVaccineInjectionRecordSet = new HBVaccineInjectionRecordSet();
		Document hbvDocument = (Document) record.get("HBVaccineInjectionRecordSet");
		
		hbVaccineInjectionRecordSet.setDateMonth0(hbvDocument.getString("DateMonth0"));
		hbVaccineInjectionRecordSet.setDateMonth1(hbvDocument.getString("DateMonth1"));
		hbVaccineInjectionRecordSet.setDateMonth2(hbvDocument.getString("DateMonth2"));
		hbVaccineInjectionRecordSet.setDateMonth3(hbvDocument.getString("DateMonth3"));
		hbVaccineInjectionRecordSet.setDateMonth4(hbvDocument.getString("DateMonth4"));
		hbVaccineInjectionRecordSet.setDateMonth5(hbvDocument.getString("DateMonth5"));
		hbVaccineInjectionRecordSet.setDateMonth6(hbvDocument.getString("DateMonth6"));
		hbVaccineInjectionRecordSet.setDateMonth7(hbvDocument.getString("DateMonth7"));
		hbVaccineInjectionRecordSet.setDateMonth8(hbvDocument.getString("DateMonth8"));
		hbVaccineInjectionRecordSet.setDateMonth9(hbvDocument.getString("DateMonth9"));
		hbVaccineInjectionRecordSet.setDateMonth10(hbvDocument.getString("DateMonth10"));
		
		patientRecord.setHbVaccineInjectionRecordSet(hbVaccineInjectionRecordSet);
		
		
		
		LaboratoryReportRecordSet laboratoryReportRecordSet = new LaboratoryReportRecordSet();
		Document lbrDocument = (Document) record.get("LaboratoryReportRecordSet");
		laboratoryReportRecordSet.setAntiHBe(lbrDocument.getString("AntiHBe"));
		laboratoryReportRecordSet.setAntiHBs(lbrDocument.getString("AntiHBs"));
		laboratoryReportRecordSet.setAntiHCV(lbrDocument.getString("AntiHCV"));
		laboratoryReportRecordSet.setHB(lbrDocument.getString("HB"));
		laboratoryReportRecordSet.setHBeAg(lbrDocument.getString("HBeAg"));
		laboratoryReportRecordSet.setVDRL(lbrDocument.getString("VDRL"));
		
		
		patientRecord.setLaboratoryReportRecordSet(laboratoryReportRecordSet);
		
		
		
		return patientRecord;
	}
	
}
