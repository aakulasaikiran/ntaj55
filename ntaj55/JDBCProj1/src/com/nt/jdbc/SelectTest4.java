package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* App giving emp details based on the given designations 
 *  version: 1.0
 *  author : Team-J
 *  Date :  7/18/2016
 */

public class SelectTest4 {

	public static void main(String[] args) {
		Scanner sc = null;
		String desg1 = null, desg2 = null, desg3 = null;
		String cond = null;
		Connection con = null;
		Statement st = null;
		String query = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Desg1::");
				desg1 = sc.next().toUpperCase(); // gives CLERK
				System.out.println("Enter Desg2::");
				desg2 = sc.next().toUpperCase(); // gives MANAGER
				System.out.println("Enter Desg3::");
				desg3 = sc.next().toUpperCase(); // gives SALESMAN
			} // if
				// Convert input values as required for SQL Query
				// ('CLERK','MANAGER','SALESMAN')
			cond = "('" + desg1 + "','" + desg2 + "','" + desg3 + "')";

			// register jdbc diver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver1");
			// establish the connection
			con = DriverManager.getConnection("jdbc:odbc:oradsn", "system", "manager");
			// create STatement obj
			if (con != null)
				st = con.createStatement();
			// prepare SQL Query
			// select empno,ename,job,sal from emp where job
			// in('CLERK','MANAGER','SALESMAN') order by job";
			query = "select empno,ename,job,sal from emp where job in" + cond + " order by job";
			System.out.println(query);
			// send and execute SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(query);
			// process the ResultSet obj
			if (rs != null) {
				while (rs.next()) {
					flag = true;
					System.out.println(
							rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4));
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
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // finally
	}// main
}// class
