package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLToOracleTest {
	private static final String INSERT_STUDENT = "INSERT INTO STUDENT VALUES(?,?,?)";
	private static final String GET_STDUENTS = "SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		Connection oraCon = null, mysqlCon = null;
		Statement mysqlSt = null;
		PreparedStatement oraPs = null;
		ResultSet mysqlRs = null;
		int no = 0;
		String name = null, addrs = null;

		try {
			// register jdbc driver s/ws
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.gjt.mm.mysql.Driver");
			// Establihs the connections
			oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			mysqlCon = DriverManager.getConnection("jdbc:mysql:///ntaj55db1", "root", "root");
			// create STatement objs
			if (mysqlCon != null)
				mysqlSt = mysqlCon.createStatement();
			if (oraCon != null)
				oraPs = oraCon.prepareStatement(INSERT_STUDENT);
			// get All the records from mysql DB table
			if (mysqlSt != null)
				mysqlRs = mysqlSt.executeQuery(GET_STDUENTS);
			// process the ResultSet
			if (mysqlRs != null && oraPs != null) {
				while (mysqlRs.next()) {
					// read each record ResultSet (mysql DB table)
					no = mysqlRs.getInt(1);
					name = mysqlRs.getString(2);
					addrs = mysqlRs.getString(3);
					// set each above record INSERT SQL Query params(oracle
					// table)
					oraPs.setInt(1, no);
					oraPs.setString(2, name);
					oraPs.setString(3, addrs);
					// execute the Query (INSERT)
					oraPs.executeUpdate();
				} // while
			} // if
			System.out.println("All Records are copied from mysql To oracle");
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (mysqlRs != null)
					mysqlRs.close();
			} // try
			catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (mysqlSt != null)
					mysqlSt.close();
			} // try
			catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (oraPs != null)
					oraPs.close();
			} // try
			catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (mysqlCon != null)
					mysqlCon.close();
			} // try
			catch (Exception se) {
				se.printStackTrace();
			}
			try {
				if (oraCon != null)
					oraCon.close();
			} // try
			catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}// main
}// class
