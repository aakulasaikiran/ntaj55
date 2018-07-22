package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AggragateTest {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		try{
			//register driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:odbc:oradsn", "system", "manager");
			//crete Statement obj
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query in Db s/w
			query="select count(*) from emp";
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSEt
			if(rs!=null){
				if(rs.next()){
					count=rs.getInt("count(*)");
				}//if
		   }//if
			System.out.println("Records count:::"+count);
		}//try
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
	}//main
}//class
