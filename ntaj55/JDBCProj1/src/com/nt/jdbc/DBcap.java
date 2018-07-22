//DatabaseMetaData program
package com.nt.jdbc;
import java.sql.*;

public class DBcap
{
public static void main(String args[])
{
	Connection con=null;
	DatabaseMetaData dbmd=null;
	try{
		//register jdbc driver s/w
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 //create DatabaseMetaData obj
		 if(con!=null)
			 dbmd=con.getMetaData();
		 //use DatabaseMetaData obj
		 if(dbmd!=null){
			 System.out.println("dbmd obj class name:"+dbmd.getClass());
				
				System.out.println("database name "+dbmd.getDatabaseProductName());
					System.out.println("database version "+dbmd.getDatabaseProductVersion());
					System.out.println("jdbc driver version "+dbmd.getDriverVersion());
					System.out.println("sql key words = "+dbmd.getSQLKeywords());
					System.out.println("numeric functions "+dbmd.getNumericFunctions());
					System.out.println("String Functions "+dbmd.getStringFunctions());
					System.out.println("System Functions"+dbmd.getSystemFunctions());
					System.out.println("Search String Escape"+dbmd.getSearchStringEscape());
					System.out.println(" supportsStoredProcedures = "+dbmd.supportsStoredProcedures());
					System.out.println(" getMaxRowSize = "+dbmd.getMaxRowSize());
					System.out.println(" getMaxStatementLength = "+dbmd.getMaxStatementLength());
					System.out.println("get Max tables in a select query="+dbmd.getMaxTablesInSelect());
					System.out.println("get Max Length of Table Name="+dbmd.getMaxTableNameLength());
					System.out.println("jdbc api version is"+dbmd.getJDBCMajorVersion()+" . "+dbmd.getJDBCMinorVersion());
		 }
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
			 if(con!=null)
				 con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}

	}//finally
		
	 }	//main
}//class
