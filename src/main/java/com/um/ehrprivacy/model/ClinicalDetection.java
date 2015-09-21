package com.um.ehrprivacy.model;

/**
 *  Clinical Detection
 *  
 * @author peterliu
 *
 */
public class ClinicalDetection {
	
	private String detectionId;
	private String patientId;
	private String detectedDate;
	private String times;
	private String AntiHBs;
	private String HBsAg;
	private String HIV;
	private String HDV;
	private String HCV;
	private String hospitalId;
	public String getDetectionId() {
		return detectionId;
	}
	public void setDetectionId(String detectionId) {
		this.detectionId = detectionId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDetectedDate() {
		return detectedDate;
	}
	public void setDetectedDate(String detectedDate) {
		this.detectedDate = detectedDate;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getAntiHBs() {
		return AntiHBs;
	}
	public void setAntiHBs(String antiHBs) {
		AntiHBs = antiHBs;
	}
	public String getHBsAg() {
		return HBsAg;
	}
	public void setHBsAg(String hBsAg) {
		HBsAg = hBsAg;
	}
	public String getHIV() {
		return HIV;
	}
	public void setHIV(String hIV) {
		HIV = hIV;
	}
	public String getHDV() {
		return HDV;
	}
	public void setHDV(String hDV) {
		HDV = hDV;
	}
	public String getHCV() {
		return HCV;
	}
	public void setHCV(String hCV) {
		HCV = hCV;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
}
