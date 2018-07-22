package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type5Test {
 private static final String QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
	try{
		//register jdbc driver class
		Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;ServiceName=xe",
				                                                              "system","manager");
		//create Statement obj
		if(con!=null)
			st=con.createStatement();
		//send and execute SQL Query in DB s/w
		if(st!=null)
			rs=st.executeQuery(QUERY);
		//process the ResultSet
		if(rs!=null){
			while(rs.next()){
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			}//while
		}//if
	}//try
	 catch(SQLException se){
		 se.printStackTrace();
	 }
/*	catch(ClassNotFoundException cnf){
		cnf.printStackTrace();
	}*/
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
