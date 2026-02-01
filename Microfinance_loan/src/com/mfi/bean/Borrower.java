package com.mfi.bean;

public class Borrower {
	private String borrowerID;
	private String fullName ;
	private String gender; 
	private java.sql.Date dateOfBirth;
	private String primaryPhone;
	private String villageOrArea; 
	private String riskCategory ;
	private String status ;
	public String getBorrowerID() {
		return borrowerID;
	}
	public void setBorrowerID(String borrowerID) {
		this.borrowerID = borrowerID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	public String getVillageOrArea() {
		return villageOrArea;
	}
	public void setVillageOrArea(String villageOrArea) {
		this.villageOrArea = villageOrArea;
	}
	public String getRiskCategory() {
		return riskCategory;
	}
	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
