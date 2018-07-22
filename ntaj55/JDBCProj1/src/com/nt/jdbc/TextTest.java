package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TextTest {

	public static void main(String[] args) {
		Connection con=null;
		   Statement st=null;
		   ResultSet rs=null;
		   boolean flag=false;
			try{
		  //register jdbc driver
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		  //establish the connection
				con=DriverManager.getConnection("jdbc:odbc:txtdsn");
				//create Statment obj
	         if(con!=null)
	        	 st=con.createStatement();
	         //send and execute sql Query
	         if(st!=null)
	        	 rs=st.executeQuery("select * from file1.csv");
	         //process the ResutlSet
	         if(rs!=null){
	           while(rs.next()){
	        	   flag=true;
	        	   System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
	           }//while
			}//if
	         if(flag==false)
	        	 System.out.println("records not found");
			}//try
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			catch(ClassNotFoundException cnf){
				cnf.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					if(rs!=null)
						rs.close();
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
				try{
					if(st!=null)
						st.close();
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
				try{
					if(con!=null)
						con.close();
				}//try
				catch(SQLException se){
					se.printStackTrace();
				}
			}//finally
   	}//main
}//class
