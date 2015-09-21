package com.um.ehrprivacy.model;

/**
 *  Patient information model.
 *  
 * @author peterliu
 *
 */
public class PatientInfo {
	
	private String PID;
	private String Name;
	private String Surname;
	private String Gender;
	private String DoBDate;
	private String Country;
	private String StreetAddress;
	private String City;
	private String Postal;
	private String hospitalId;
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getDoBDate() {
		return DoBDate;
	}
	public void setDoBDate(String doBDate) {
		DoBDate = doBDate;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getStreetAddress() {
		return StreetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		StreetAddress = streetAddress;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getPostal() {
		return Postal;
	}
	public void setPostal(String postal) {
		Postal = postal;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
}
