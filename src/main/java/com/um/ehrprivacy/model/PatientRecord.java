package com.um.ehrprivacy.model;

/**
 *  This is the structure of the public patient records.
 *  	
 * @author peterliu
 *
 */
public class PatientRecord {
	
	private String recordID;
	
	private String hospitalID;
	
	private PatientInfo patientInfo;
	
	private String examination;
	
	private String diagnose;
	
	private String doctor;
	
	private String privacy; // Control the privacy of patient record

	public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	public String getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	
	
}
