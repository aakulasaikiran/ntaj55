package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

public class CsCursorTest {
private static final String CALL_PROCEDURE="{ call FETCH_EMP_DETAILS(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
	 try{
		 //read inputs
		 sc=new Scanner(System.in);
		 if(sc!=null){
			 System.out.println("Enter emp name initial chars::");
			 initChars=sc.next();
		 }
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System", "manager");
			//create CallableStatement obj
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			//register OUT params with jdbc types  (oracle supplied jdbc type)
			if(cs!=null)
				cs.registerOutParameter(2,OracleTypes.CURSOR);
			//set values to IN params
			if(cs!=null)
				cs.setString(1,initChars+"%");
			//call pl/sql procedure
			if(cs!=null)
				cs.execute();
			//gather results from OUT param
			if(cs!=null)
			  rs=(ResultSet)cs.getObject(2);   //(Resultset hoiding cursor records)
			//process the ResultSet
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"  "+rs.getInt(4));
				}//while
			}//if
			if(flag==false)
				System.out.println("No records found ");
			
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
				 if(rs!=null)
					 rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
			 if(cs!=null)
				 cs.close();
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