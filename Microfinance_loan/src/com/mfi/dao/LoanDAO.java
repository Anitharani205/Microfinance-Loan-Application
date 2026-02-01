package com.mfi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mfi.bean.Loan;
import com.mfi.util.DBUtil;

public class LoanDAO {

	public Loan findLoan(String loanID)
	{
		Loan l=null;
	try {
		Connection con = DBUtil.getDBConnection();
		String query="SELECT * FROM LOAN_TBL WHERE loanID=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, loanID);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
		    l = new Loan();
			l.setLoanID(rs.getString("loanID"));
            l.setBorrowerID(rs.getString("borrowerID"));
            l.setProductName(rs.getString("productName"));
            l.setPrincipalAmount(rs.getBigDecimal("principalAmount"));
            l.setAnnualInterestRate(rs.getBigDecimal("annualInterestRate"));
            l.setTermMonths(rs.getInt("termMonths"));
            l.setRepaymentFrequency(rs.getString("repaymentFrequency"));
            l.setDisbursementDate(rs.getDate("disbursementDate"));
            l.setOutstandingPrincipal(rs.getBigDecimal("outstandingPrincipal"));
            l.setStatus(rs.getString("status"));
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	return l;
	}
	
	public java.util.List<Loan> viewAllLoans() 
	{
		List<Loan> loan = new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			String query="SELECT * FROM LOAN_TBL";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Loan l = new Loan();
				l.setLoanID(rs.getString("loanID"));
	            l.setBorrowerID(rs.getString("borrowerID"));
	            l.setProductName(rs.getString("productName"));
	            l.setPrincipalAmount(rs.getBigDecimal("principalAmount"));
	            l.setAnnualInterestRate(rs.getBigDecimal("annualInterestRate"));
	            l.setTermMonths(rs.getInt("termMonths"));
	            l.setRepaymentFrequency(rs.getString("repaymentFrequency"));
	            l.setDisbursementDate(rs.getDate("disbursementDate"));
	            l.setOutstandingPrincipal(rs.getBigDecimal("outstandingPrincipal"));
	            l.setStatus(rs.getString("status"));
	            loan.add(l);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return loan;
		}
	public java.util.List<Loan> viewActiveLoan()
	{
		List<Loan> loan = new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			String query="SELECT * FROM LOAN_TBL WHERE status=ACTIVE";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Loan l = new Loan();
				l.setLoanID(rs.getString("loanID"));
	            l.setBorrowerID(rs.getString("borrowerID"));
	            l.setProductName(rs.getString("productName"));
	            l.setPrincipalAmount(rs.getBigDecimal("principalAmount"));
	            l.setAnnualInterestRate(rs.getBigDecimal("annualInterestRate"));
	            l.setTermMonths(rs.getInt("termMonths"));
	            l.setRepaymentFrequency(rs.getString("repaymentFrequency"));
	            l.setDisbursementDate(rs.getDate("disbursementDate"));
	            l.setOutstandingPrincipal(rs.getBigDecimal("outstandingPrincipal"));
	            l.setStatus(rs.getString("status"));
	            loan.add(l);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return loan;
		}
	public boolean insertLoan(Loan loan) 
	{
		try {
	    		 Connection con = DBUtil.getDBConnection();
	    		 String query = "INSERT INTO LOAN_TBL (loanID,borrowerID,productName,principalAmount,annualInterestRate,termMonths,repaymentFrequency,disbursementDate,outstandingPrincipal,status) VALUES(?,?,?,?,?,?,?,?,?,?)";
	    		 PreparedStatement ps = con.prepareStatement(query);
	    		 ps.setString(1,loan.getLoanID());
	    		 ps.setString(2,loan.getBorrowerID());
	    		 ps.setString(3,loan.getProductName());
	    		 ps.setBigDecimal(4, loan.getPrincipalAmount());
	    		 ps.setBigDecimal(5, loan.getAnnualInterestRate());
	    		 ps.setInt(6, loan.getTermMonths());
	    		 ps.setString(7, loan.getRepaymentFrequency());
	    		 ps.setDate(8,loan.getDisbursementDate());
	    		 ps.setBigDecimal(9,loan.getOutstandingPrincipal());
	    		 ps.setString(10,loan.getStatus());
	    		 
	    		 int rows=ps.executeUpdate();
	    		 if(rows > 0)
	    		 {
	    			 return true;
	    		 }
	    		 else {
	    			 return false;
	    		 }
	    	 }
	    	 catch(SQLException e)
	    	 {
	    		 e.printStackTrace(); 
	    	 }
	    	 return false;
	   
	}
	public boolean updateLoanStatus(String loanID,String status) 
	{
		try {
		Connection con = DBUtil.getDBConnection();
		String query=" UPDATE LOAN_TBL SET status=? WHERE loanId = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, status);
		ps.setString(2, loanID);
		int rows = ps.executeUpdate();
		if(rows > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateOutstandingPrincipal(String loanID,java.math.BigDecimal newOutstanding)
	{
		try {
			Connection con = DBUtil.getDBConnection();
			String query="UPDATE LOAN_TBL SET newOutstanding WHERE loanID=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setBigDecimal(1,newOutstanding);
			ps.setString(2, loanID);
			int rows = ps.executeUpdate();
			if(rows > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public java.util.List<Loan> findActiveLoansByBorrower(String borrowerID) 
	{
		List<Loan> loan=new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			String query = "SELECT * FROM LOAN_TBL WHERE borrowerID=? AND status='ACTIVE'";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, borrowerID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Loan l = new Loan();
				l.setLoanID(rs.getString("loanID"));
	            l.setBorrowerID(rs.getString("borrowerID"));
	            l.setProductName(rs.getString("productName"));
	            l.setPrincipalAmount(rs.getBigDecimal("principalAmount"));
	            l.setAnnualInterestRate(rs.getBigDecimal("annualInterestRate"));
	            l.setTermMonths(rs.getInt("termMonths"));
	            l.setRepaymentFrequency(rs.getString("repaymentFrequency"));
	            l.setDisbursementDate(rs.getDate("disbursementDate"));
	            l.setOutstandingPrincipal(rs.getBigDecimal("outstandingPrincipal"));
	            l.setStatus(rs.getString("status"));
	            loan.add(l);
	    		 
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return loan;
	}
	}


