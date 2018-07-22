package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			//register jdbc driver
			Class.forName("org.gjt.mm.mysql.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj55db1","root","root");
			//create STatement obj
			if(con!=null)
			  st=con.createStatement();
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery("select * from student");
			//process the ReusltSet
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+" "+rs.getString(3));
				}//while
			}//if
			if(flag==false)
				System.out.println("No Records found");
			
			System.out.println("con object class name::"+con.getClass());
			System.out.println("st object class name::"+st.getClass());
			System.out.println("rs obj class name::"+rs.getClass());
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
