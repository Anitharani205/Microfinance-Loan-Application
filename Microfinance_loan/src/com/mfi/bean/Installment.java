package com.mfi.bean;

public class Installment {
	private int installmentID; 
	 private String loanID;
	 private int installmentNo; 
	 private java.sql.Date dueDate;
	 private java.math.BigDecimal dueAmount;
	 private java.math.BigDecimal paidAmount;
	 private java.sql.Date paidDate;
	 private String status;
	 public int getInstallmentID() {
		 return installmentID;
	 }
	 public void setInstallmentID(int installmentID) {
		 this.installmentID = installmentID;
	 }
	 public String getLoanID() {
		 return loanID;
	 }
	 public void setLoanID(String loanID) {
		 this.loanID = loanID;
	 }
	 public int getInstallmentNo() {
		 return installmentNo;
	 }
	 public void setInstallmentNo(int installmentNo) {
		 this.installmentNo = installmentNo;
	 }
	 public java.sql.Date getDueDate() {
		 return dueDate;
	 }
	 public void setDueDate(java.sql.Date dueDate) {
		 this.dueDate = dueDate;
	 }
	 public java.math.BigDecimal getDueAmount() {
		 return dueAmount;
	 }
	 public void setDueAmount(java.math.BigDecimal dueAmount) {
		 this.dueAmount = dueAmount;
	 }
	 public java.math.BigDecimal getPaidAmount() {
		 return paidAmount;
	 }
	 public void setPaidAmount(java.math.BigDecimal paidAmount) {
		 this.paidAmount = paidAmount;
	 }
	 public java.sql.Date getPaidDate() {
		 return paidDate;
	 }
	 public void setPaidDate(java.sql.Date paidDate) {
		 this.paidDate = paidDate;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
}
