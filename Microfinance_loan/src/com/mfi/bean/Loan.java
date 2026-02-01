package com.mfi.bean;

public class Loan {
	private String loanID; 
	 private String borrowerID;
	 private String productName; 
	 private java.math.BigDecimal principalAmount;
	 private java.math.BigDecimal annualInterestRate;
	 private int termMonths;
	 private String repaymentFrequency;
	 private java.sql.Date disbursementDate;
	 private java.math.BigDecimal outstandingPrincipal;
	 private String status;
	 public String getLoanID() {
		 return loanID;
	 }
	 public void setLoanID(String loanID) {
		 this.loanID = loanID;
	 }
	 public String getBorrowerID() {
		 return borrowerID;
	 }
	 public void setBorrowerID(String borrowerID) {
		 this.borrowerID = borrowerID;
	 }
	 public String getProductName() {
		 return productName;
	 }
	 public void setProductName(String productName) {
		 this.productName = productName;
	 }
	 public java.math.BigDecimal getPrincipalAmount() {
		 return principalAmount;
	 }
	 public void setPrincipalAmount(java.math.BigDecimal principalAmount) {
		 this.principalAmount = principalAmount;
	 }
	 public java.math.BigDecimal getAnnualInterestRate() {
		 return annualInterestRate;
	 }
	 public void setAnnualInterestRate(java.math.BigDecimal annualInterestRate) {
		 this.annualInterestRate = annualInterestRate;
	 }
	 public int getTermMonths() {
		 return termMonths;
	 }
	 public void setTermMonths(int termMonths) {
		 this.termMonths = termMonths;
	 }
	 public String getRepaymentFrequency() {
		 return repaymentFrequency;
	 }
	 public void setRepaymentFrequency(String repaymentFrequency) {
		 this.repaymentFrequency = repaymentFrequency;
	 }
	 public java.sql.Date getDisbursementDate() {
		 return disbursementDate;
	 }
	 public void setDisbursementDate(java.sql.Date disbursementDate) {
		 this.disbursementDate = disbursementDate;
	 }
	 public java.math.BigDecimal getOutstandingPrincipal() {
		 return outstandingPrincipal;
	 }
	 public void setOutstandingPrincipal(java.math.BigDecimal outstandingPrincipal) {
		 this.outstandingPrincipal = outstandingPrincipal;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
}
