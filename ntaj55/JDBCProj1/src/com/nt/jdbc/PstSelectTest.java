package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/* App giving emp details based on the given designations 
 *  version: 1.0
 *  author : Team-J
 *  Date :  7/18/2016
 */

public class PstSelectTest {
	private static final String GET_EMPS_BY_DESG="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN(?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		String desg1 = null, desg2 = null, desg3 = null;
		String cond = null;
		Connection con = null;
		PreparedStatement ps = null;
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
			

			// register jdbc diver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create STatement obj
			if (con != null)
				ps = con.prepareStatement(GET_EMPS_BY_DESG);
			//set values Query params(?)
			if(ps!=null){
				ps.setString(1,desg1);
				ps.setString(2,desg2);
				ps.setString(3,desg3);
			}
		
			// send and execute SQL Query in DB s/w
			if (ps != null)
				rs = ps.executeQuery();
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
				if (ps != null)
					ps.close();
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
