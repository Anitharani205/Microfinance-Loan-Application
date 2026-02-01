package com.mfi.app;

import com.mfi.bean.Borrower;
import com.mfi.bean.Loan;
import com.mfi.service.MfiService;
import com.mfi.util.ValidationException;

public class MfiMain { 
private static MfiService service = new MfiService(); 
public static void main(String[] args) { 
java.util.Scanner sc = new java.util.Scanner(System.in); 
System.out.println("--- Microfinance Loan & Repayment Console ---"); 

try { 
Borrower b = new Borrower(); 
b.setBorrowerID("BR2005"); 
b.setFullName("Nalini Devi"); 
b.setGender("FEMALE"); 
b.setDateOfBirth(java.sql.Date.valueOf("1965-03-15")); 
b.setPrimaryPhone("9998887771"); 
b.setVillageOrArea("Sunrise Colony"); 
b.setRiskCategory("LOW"); 
b.setStatus("ACTIVE"); 
boolean ok = service.registerNewBorrower(b); 
System.out.println(ok ? "BORROWER REGISTERED" : 
"BORROWER REGISTRATION FAILED"); 
} catch (Exception e) { 
System.out.println("System Error: " + 
e.getMessage()); 
} 

try { 
Loan loan = new Loan(); 
loan.setLoanID("LN2025-014"); 
loan.setBorrowerID("BR2001"); 
loan.setProductName("Small Business Loan"); 
loan.setPrincipalAmount(new java.math.BigDecimal("40000.00")); 
loan.setAnnualInterestRate(new java.math.BigDecimal("18.00")); 
loan.setTermMonths(12); 
loan.setRepaymentFrequency("MONTHLY"); 
loan.setDisbursementDate(new java.sql.Date(System.currentTimeMillis())); 
loan.setOutstandingPrincipal(new java.math.BigDecimal("40000.00")); 
loan.setStatus("ACTIVE"); 
boolean ok = service.createLoanAccount(loan); 
System.out.println(ok ? "LOAN CREATED" : "LOAN CREATION FAILED"); 
} catch (Exception e) { 
System.out.println("System Error: " + 
e.getMessage()); 
} 
sc.close(); 
} 
}