package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginApp1 {
private static final String AUTHENTION_QUERY="SELECT COUNT(*) FROM USERLIST WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		//read inputs
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter username::");
				user=sc.nextLine(); //gives raja
				System.out.println("Enter password::");
				pass=sc.nextLine(); //give rani
			}//if
			//register jdbc driver  s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(AUTHENTION_QUERY);
			//set input values to Query params
			if(ps!=null){
				ps.setString(1,user);
				ps.setString(2,pass);
			}//if
			//execute the SQL Query
			if(ps!=null){
				rs=ps.executeQuery();
			}
			//process the ResultSet
			if(rs!=null){
				if(rs.next()){
					count=rs.getInt(1);
				}//if
			}//if
			if(count==0)
				System.out.println("InValidCredentials");
			else
				System.out.println("Valid Credentials");
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
				if(ps!=null)
					ps.close();
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
			try{
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
