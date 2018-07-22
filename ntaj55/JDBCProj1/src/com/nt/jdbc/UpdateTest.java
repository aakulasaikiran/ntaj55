package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Existing student no:");
				no=sc.nextInt();
				System.out.println("Enter new name :");
				sc.nextLine();
				newName=sc.nextLine();  // gives new raja
				System.out.println("Enter new  Address:");
				newAddrs=sc.nextLine();
			}//if
			// convert input values as required for the SQL Query
			newName="'"+newName+"'"; //gives 'new raja'
			newAddrs="'"+newAddrs+"'"; //gives 'new hyd'
			//register jdbc driver s/w
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement obj
			if(con!=null)
			st=con.createStatement();
			//prepare SQL Query
			    // update student set sname='new raja',sadd='new hyd' where sno=1001
			 query="update student set sname="+newName+",sadd="+newAddrs+" where sno="+no;
			 System.out.println(query);
			 //send and execute SQL Query in Db s/w
			 if(st!=null)
				result=st.executeUpdate(query);
			 //process the result
			 if(result==0)
				 System.out.println("Record not found for updation");
			 else 
				 System.out.println("Record fiund for updation");
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
				catch(Exception e){
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
