package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectTest5 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try{
		/*	//register jdbc driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");*/
			 sun.jdbc.odbc.JdbcOdbcDriver driver=new sun.jdbc.odbc.JdbcOdbcDriver();
			 Properties props=new Properties();
			 props.put("user","system"); props.put("password","manager");
			con=driver.connect("jdbc:odbc:oradsn",props);
			 
			//create STatement obj
			if(con!=null)
			  st=con.createStatement();
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery("select empno,ename,job,sal from emp where sal=(select max(sal) from emp)");
			//process the ReusltSet
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}//while
			}//if
			if(flag==false)
				System.out.println("No Records found");
			
			System.out.println("con obj class name:"+con.getClass());
			System.out.println("st obj class name:"+st.getClass());
			System.out.println("rs obj class name:"+rs.getClass());
			
		}//try
/*		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} 
*/		catch (SQLException se) {
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
