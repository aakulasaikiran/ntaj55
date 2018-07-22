package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result=0;
		String tabName=null;
		Scanner sc=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter table name:::");
				tabName=sc.next();
			}
				
			//register jdbc driver s/w
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement obj
			if(con!=null)
			st=con.createStatement();
			//send execute SQL Query
			if(st!=null)
				result=st.executeUpdate("drop table "+tabName);
			//process the result
			if(result==0)
				System.out.println("Table not dropped");
			else
				System.out.println("Table dropeed");
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
			//close jdbc,stream objs
			try{
			  if(st!=null)
				  st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				  if(con!=null)
					  con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				  if(sc!=null)
					  sc.close();
				}
				catch(Exception se){
					se.printStackTrace();
				}
		}//finally
	}//main
}//class
