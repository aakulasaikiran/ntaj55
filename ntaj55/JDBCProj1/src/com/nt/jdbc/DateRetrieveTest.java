package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateRetrieveTest {
   private static final String  GET_PERSON_TAB_DETAILS="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_TAB";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int pid=0;
		String name=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		String dob=null,doj=null,dom=null;
		SimpleDateFormat sdf=null;
		try{
		/*	//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
			
			//register jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj55db1","root","root");
			
			//create Statement obj
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query to DB s/w
			if(st!=null)
				rs=st.executeQuery(GET_PERSON_TAB_DETAILS);
			//process the ResultSet
			if(rs!=null){
				while(rs.next()){
				  // retrive each record
					pid=rs.getInt(1);
					name=rs.getString(2);
					sqdob=rs.getDate(3);
					sqdoj=rs.getDate(4);
					sqdom=rs.getDate(5);
					// convert java.sql.Date objs to java.util.Date class objs
					udob=(java.util.Date)sqdob;
					udoj=(java.util.Date)sqdoj;
					udom=(java.util.Date)sqdom;
					//convert java.util.Date class objs to String date values
					sdf=new SimpleDateFormat("yyyy-MMM-dd");
					dob=sdf.format(udob);
					doj=sdf.format(udoj);
					dom=sdf.format(udom);
					System.out.println(pid+"   "+name+"   "+dob+"   "+doj+"   "+dom);
				}//while
			}//if
			
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
