package com.um.ehrprivacy.model;

/**
 *  This is the structure of the public patient records.
 *  	
 * @author peterliu
 *
 */
public class PatientRecord {
	
	private String HospitalID;
	private String IDCardNO;
	private String Date;
	
	private HemodialysisChartRecordSet hemodialysisChartRecordSet;
	private HemodialysisRecordSet hemodialysisRecordSet;
	
	private HBVaccineInjectionRecordSet hbVaccineInjectionRecordSet;
	private LaboratoryReportRecordSet laboratoryReportRecordSet;
	public String getHospitalID() {
		return HospitalID;
	}
	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}
	public String getIDCardNO() {
		return IDCardNO;
	}
	public void setIDCardNO(String iDCardNO) {
		IDCardNO = iDCardNO;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public HemodialysisChartRecordSet getHemodialysisChartRecordSet() {
		return hemodialysisChartRecordSet;
	}
	public void setHemodialysisChartRecordSet(HemodialysisChartRecordSet hemodialysisChartRecordSet) {
		this.hemodialysisChartRecordSet = hemodialysisChartRecordSet;
	}
	public HemodialysisRecordSet getHemodialysisRecordSet() {
		return hemodialysisRecordSet;
	}
	public void setHemodialysisRecordSet(HemodialysisRecordSet hemodialysisRecordSet) {
		this.hemodialysisRecordSet = hemodialysisRecordSet;
	}
	public HBVaccineInjectionRecordSet getHbVaccineInjectionRecordSet() {
		return hbVaccineInjectionRecordSet;
	}
	public void setHbVaccineInjectionRecordSet(HBVaccineInjectionRecordSet hbVaccineInjectionRecordSet) {
		this.hbVaccineInjectionRecordSet = hbVaccineInjectionRecordSet;
	}
	public LaboratoryReportRecordSet getLaboratoryReportRecordSet() {
		return laboratoryReportRecordSet;
	}
	public void setLaboratoryReportRecordSet(LaboratoryReportRecordSet laboratoryReportRecordSet) {
		this.laboratoryReportRecordSet = laboratoryReportRecordSet;
	}
	
}
