package com.mfi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mfi.bean.Borrower;
import com.mfi.util.DBUtil;

public class BorrowerDAO {
     public Borrower findBorrower(String borrowerId)
     {
    	 Borrower borrower = null;
    	 try {
    	 Connection con = DBUtil.getDBConnection();
    	 String query = "SELECT * FROM BORROWER_TBL WHERE borrowerId=?";
    	 PreparedStatement ps = con.prepareStatement(query);
    	 ps.setString(1,borrowerId);
    	 ResultSet rs=ps.executeQuery();
    	 while(rs.next())
    	 {
    		  borrower=new Borrower();
    		 borrower.setBorrowerID(rs.getString("borrowerId"));
    		 borrower.setFullName(rs.getString("fullname"));
    		 borrower.setGender(rs.getString("gender"));
    		 borrower.setStatus(rs.getString("status"));
    		 borrower.setRiskCategory(rs.getString("riskCategory")); 
    		 
    	 }
     }
    	 catch(SQLException e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return borrower;
     }
     
     public java.util.List<Borrower> viewAllBorrowers()
     {
    	 List<Borrower> borrower = new ArrayList<>();
    	 try {
    		 Connection con = DBUtil.getDBConnection();
    		 String query = "SELECT * FROM BORROWER_TBL";
    		 PreparedStatement ps = con.prepareStatement(query);
    		 ResultSet rs = ps.executeQuery();
    		 while(rs.next())
    		 {
    			 Borrower b = new Borrower();
    			 b.setBorrowerID(rs.getString("borrowerId"));
        		 b.setFullName(rs.getString("fullname"));
        		 b.setGender(rs.getString("gender"));
        		 b.setStatus(rs.getString("status"));
        		 b.setRiskCategory(rs.getString("riskCategory")); 
        		 b.setDateOfBirth(rs.getDate("dateOfBirth"));
        		 b.setPrimaryPhone(rs.getString("primaryPhone"));
        		 borrower.add(b);
        		 
    		 }
    	 }
    	 catch(SQLException e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return borrower;
     }
     
     public boolean insertBorrower(Borrower borrower)
     {
    	 try {
    		 Connection con = DBUtil.getDBConnection();
    		 String query = "INSERT INTO BORROWER_TBL (borrowerID,fullname,gender,dateOfBirth,primaryPhone,VillageArea,riskCategory,status) VALUES(?,?,?,?,?,?,?,?)";
    		 PreparedStatement ps = con.prepareStatement(query);
    		 ps.setString(1,borrower.getBorrowerID());
    		 ps.setString(2,borrower.getFullName());
    		 ps.setString(3,borrower.getGender());
    		 ps.setDate(4, borrower.getDateOfBirth());
    		 ps.setString(5, borrower.getPrimaryPhone());
    		 ps.setString(6, borrower.getVillageOrArea());
    		 ps.setString(7, borrower.getRiskCategory());
    		 ps.setString(8, borrower.getStatus());
    		 
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
     
     public boolean updateBorrowerStatus(String borrowerID,String status)
     {
    	 try {
    		 Connection con = DBUtil.getDBConnection();
    		 String query = "UPDATE BORROWER_TBL SET status= ? WHERE borrowerID=?";
    		 PreparedStatement ps = con.prepareStatement(query);
    		 ps.setString(1, status);
    		 ps.setString(2,borrowerID);
    		 int rows=ps.executeUpdate();
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
     
     public boolean deleteBorrower(String  borrowerID)
     {
    	 try {
    		 Connection con = DBUtil.getDBConnection();
    		 String query = "DELETE FROM BORROWER_TBL WHERE borrowerID=?";
    		 PreparedStatement ps = con.prepareStatement(query);
    		 ps.setString(1,borrowerID);
    		 int rows=ps.executeUpdate();
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
     
     
     }

