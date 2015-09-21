package com.um.ehrprivacy.model;

/**
 *  Examination
 *  
 * @author peterliu
 *
 */
public class Examination {
	
	private String reportId;
	private String patientId;
	private String endoscopyDate;
	private String diagnosesText;
	private String doctor;
	private String hospitalId;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getEndoscopyDate() {
		return endoscopyDate;
	}
	public void setEndoscopyDate(String endoscopyDate) {
		this.endoscopyDate = endoscopyDate;
	}
	public String getDiagnosesText() {
		return diagnosesText;
	}
	public void setDiagnosesText(String diagnosesText) {
		this.diagnosesText = diagnosesText;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
	
	
}
