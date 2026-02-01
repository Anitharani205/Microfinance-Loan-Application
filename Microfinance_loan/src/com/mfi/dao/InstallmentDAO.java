package com.mfi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mfi.bean.Borrower;
import com.mfi.bean.Installment;
import com.mfi.util.DBUtil;

public class InstallmentDAO {
	public int generateInstallmentID()
	{
		String query="SELECT INSTALLMENTID_SEQ.NEXTVAL from dual";
		try {
			Connection con = DBUtil.getDBConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		throw new RuntimeException("Unable to generate ID");
	}
	public boolean insertInstallment(Installment installment)
	{
		try {
			Connection con=DBUtil.getDBConnection();
			String query = "INSERT INTO INSTALLMENT_TBL(installmentID,loanID,installmentNo,dueDate,dueAmount,paidAmount,paidDate,status) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,installment.getInstallmentID());
			ps.setString(2,installment.getLoanID());
			ps.setInt(3, installment.getInstallmentNo());
			ps.setDate(4,installment.getDueDate());
			ps.setBigDecimal(5,installment.getDueAmount());
			ps.setBigDecimal(6,installment.getPaidAmount());
			ps.setDate(7,installment.getPaidDate());
			ps.setString(8,installment.getStatus());
			
			int rows = ps.executeUpdate();
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
  
	public Installment findInstallment(int installmentID)
	{
		 Installment install = null;
    	 try {
    	 Connection con = DBUtil.getDBConnection();
    	 String query = "SELECT * FROM INSTALLMENT_TBL WHERE installmentID=?";
    	 PreparedStatement ps = con.prepareStatement(query);
    	 ps.setInt(1,installmentID);
    	 ResultSet rs=ps.executeQuery();
    	 while(rs.next())
    	 {
    		  install=new Installment();
    		  install.setInstallmentID(rs.getInt("installmentID"));
    		  install.setLoanID(rs.getString("loanID"));
    		  install.setInstallmentNo(rs.getInt("installmentNo"));
    		  install.setDueDate(rs.getDate("dueDate"));
    		  install.setDueAmount(rs.getBigDecimal("dueAmount"));
    		  install.setPaidAmount(rs.getBigDecimal("paidAmount"));
    		  install.setPaidDate(rs.getDate("paidDate"));
    		  install.setStatus(rs.getString("status"));
    	 }
     }
    	 catch(SQLException e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return install;
	}
	public List<Installment> findInstallmentsByLoan(String loanID)
	{
		List<Installment> install = new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			String query = "SELECT * FROM INSTALLMENT_TBL WHERE loanID=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, loanID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Installment i = new Installment();
				i.setInstallmentID(rs.getInt("installmentID"));
				i.setLoanID(rs.getString("loanID"));
				i.setInstallmentNo(rs.getInt("installmentNo"));
				i.setDueDate(rs.getDate("dueDate"));
				i.setDueAmount(rs.getBigDecimal("dueAmount"));
				i.setPaidAmount(rs.getBigDecimal("paidAmount"));
				i.setPaidDate(rs.getDate("paidDate"));
				i.setStatus(rs.getString("status"));
				
				install.add(i);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return install;
	}
	public List<Installment> findPendingInstallmentsByLoan(String loanID)
	{
		List<Installment> install = new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			String query = "SELECT * FROM INSTALLMENT_TBL WHERE loanID=? AND status IN ('PENDING','PARTIALLY_PAID')";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, loanID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Installment i = new Installment();
				i.setInstallmentID(rs.getInt("installmentID"));
				i.setLoanID(rs.getString("loanID"));
				i.setInstallmentNo(rs.getInt("installmentNo"));
				i.setDueDate(rs.getDate("dueDate"));
				i.setDueAmount(rs.getBigDecimal("dueAmount"));
				i.setPaidAmount(rs.getBigDecimal("paidAmount"));
				i.setPaidDate(rs.getDate("paidDate"));
				i.setStatus(rs.getString("status"));
				
				install.add(i);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return install;
	}
	public boolean updateInstallmentPayment(int installmentID,java.math.BigDecimal newPaidAmount,java.sql.Date 
	paidDate,String newStatus) 
	{
		try {
			Connection con = DBUtil.getDBConnection();
			String query = "UPDATE INSTALLMENT_TBL SET newPaidAmount=?,paidDate=?,newStatus=? WHERE installmentID=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setBigDecimal(1,newPaidAmount);
			ps.setDate(2, paidDate);
			ps.setString(3, newStatus);
			ps.setInt(4, installmentID);
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
	public java.util.List<Installment> findOverdueInstallments(java.sql.Date referenceDate) 
	{
		List<Installment> install = new ArrayList<>();
		try {
			Connection con = DBUtil.getDBConnection();
			String query = "SELECT * FROM INSTALLMENT_TBL WHERE dueDate < ? AND status <> 'PAID'";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1,referenceDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Installment i = new Installment();
				i.setInstallmentID(rs.getInt("installmentID"));
				i.setLoanID(rs.getString("loanID"));
				i.setInstallmentNo(rs.getInt("installmentNo"));
				i.setDueDate(rs.getDate("dueDate"));
				i.setDueAmount(rs.getBigDecimal("dueAmount"));
				i.setPaidAmount(rs.getBigDecimal("paidAmount"));
				i.setPaidDate(rs.getDate("paidDate"));
				i.setStatus(rs.getString("status"));
				install.add(i);
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return install;
	}
	
}
