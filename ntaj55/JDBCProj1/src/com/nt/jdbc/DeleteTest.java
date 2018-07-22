package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student no:");
				no=sc.nextInt();
			}//if
			//register driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Establihs the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create JDBC Statetment 
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			      // delete from student  where sno=101;
			query="delete from student  where sno="+no;
			System.out.println(query);
			//send execute Query in DB s/w (non-select)
			if(st!=null)
				result=st.executeUpdate(query);
			//process Result
			if(result==0)
				System.out.println("No Record found for deletion");
			else
				System.out.println("Record found and deleted");
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
			//close jdbc objs
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
