package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTest {
 private static final String INSERT_DATES="INSERT INTO PERSON_TAB VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		int id=0;
		String name=null,dob=null,doj=null,dom=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		java.util.Date udob=null,udom=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		long ms=0;
		PreparedStatement ps=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Persion id");
				id=sc.nextInt();
				System.out.println("Enter Person name");
				name=sc.next();
				System.out.println("Enter person DOB(dd-MM-yyyy)");
				dob=sc.next();
				System.out.println("Enter person DOJ(yyyy-MM-dd)");
				doj=sc.next();
				System.out.println("Enter person DOM(MM-dd-yyyy)");
				dom=sc.next();
			}//if
			//convert date values to java.sql.Date class objs
			// for DOB (dd-MM-yyyy)
			sdf1=new SimpleDateFormat("dd-MM-yyyy");
			udob=sdf1.parse(dob); //gives util date
			
			ms=udob.getTime();
			sqdob=new java.sql.Date(ms);  //gives sql Date
			//for DOJ
			sqdoj=java.sql.Date.valueOf(doj); //gives sql date
			//for DOM
			sdf2=new SimpleDateFormat("MM-dd-yyyy");
			udom=sdf2.parse(dom); //gives util date
			
			ms=udom.getTime();
			sqdom=new java.sql.Date(ms);  //gives sql Date
			
			//register jdbc driver
			Class.forName("org.gjt.mm.mysql.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj55db1","root","root");
	/*		Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establihs the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
			//create PreparedStatement object 
			if(con!=null)
			  ps=con.prepareStatement(INSERT_DATES);
			//set values to Query params
			 if(ps!=null){
				 ps.setInt(1,id);
				 ps.setString(2,name);
				 ps.setDate(3,sqdob);
				 ps.setDate(4,sqdoj);
				 ps.setDate(5,sqdom);
			 }
			 //excute the Query
			 if(ps!=null){
				 result=ps.executeUpdate();
			 }
			 if(result==0)
				 System.out.println("Record  not inserted");
			 else
				 System.out.println("Record inserted");
		}//try
		 catch(SQLException se){
			 se.printStackTrace();
			 System.out.println("Record not recorded");
		 }
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
