package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFxTest {
	private static final String FX_QUERY="{?=call FX_GET_EMP_DETAILS(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null; 
		int no=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Emp no:");
				no=sc.nextInt();
			}//if
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System", "manager");	
			//create Callable Statement obj
			if(con!=null)
				cs=con.prepareCall(FX_QUERY);
			//register OUT,return params with JDBC types
			if(cs!=null){
				cs.registerOutParameter(1,Types.VARCHAR);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4,Types.INTEGER);
			}
			//set values to IN params
			if(cs!=null){
				cs.setInt(2,no);
			}
			//call pl/sql function
			if(cs!=null)
			  cs.execute();
			//gather results from return ,OUT params
			if(cs!=null){
				System.out.println("Emp name:::"+cs.getString(3));
				System.out.println("Emp Desg:::"+cs.getString(1));
				System.out.println("Emp salary:::"+cs.getInt(4));
			}//if
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
