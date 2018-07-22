package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/* App giving emp details based on the given designations 
 *  version: 1.0
 *  author : Team-J
 *  Date :  7/18/2016
 */

public class ResultSetMetaDataApp {
  private static final String QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		boolean flag=false;
		try {
			// register jdbc diver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create STatement obj
			if (con != null)
				st = con.createStatement();
			// send and execute SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(QUERY);
			//create ResultSetMetaData obj
			if(rs!=null)
				rsmd=rs.getMetaData();
			//print col names
			if(rsmd!=null){
			  colCount=rsmd.getColumnCount();
			  for(int i=1;i<=colCount;++i){
				  System.out.print(rsmd.getColumnLabel(i)+"   ");
			  }//for
			}//if
			 System.out.println();
			// process the ResultSet obj
			if (rs != null) {
				while (rs.next()) {
					flag=true;
			         for(int i=1;i<=colCount;++i){
			        	 System.out.print(rs.getString(i)+"   ");
			         }//for
			         System.out.println();
				} // while
			} // if
			if (flag == false)
				System.out.println("No Records found");
		} // try
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		
		} // finally
	}// main
}// class
