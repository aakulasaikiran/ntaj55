package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null;
		String addrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int  result=0;
		try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student no:");
			no=sc.nextInt(); //gives 101
			System.out.println("Enter student name:");
			name=sc.next(); //gives raja
			System.out.println("Enter student address");
			addrs=sc.next(); // gives hyd
		}//if
		//convert input values as required for SQLQuery
		name="'"+name+"'";  //gives 'raja'
		addrs="'"+addrs+"'"; //gives  'hyd'
		//register driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Establihs the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create STatement obj
		if(con!=null)
			st=con.createStatement();
		//prepare SQL Query
		   // insert into student values(1001,'raja','hyd')
		query=" insert into student values("+no+","+name+","+addrs+")";
		System.out.println(query);
		//send and exeucte SQL Query in Db s/w
		if(st!=null)
			result=st.executeUpdate(query);
		//process the Result
		if(result==0)
			System.out.println("Record is not inserted");
		else
			System.out.println("record inserted");
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
