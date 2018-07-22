package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePointTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		Savepoint sp=null;
		try{
			//register jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create Statement obj
			 if(con!=null)
				 st=con.createStatement();
			 //begin Tx
			 if(con!=null)
			  con.setAutoCommit(false);
			  //query1
			 if(st!=null && con!=null){
			 st.executeUpdate("INSERT INTO STUDENT VALUES(1001,'raja','vizag')");
			  //create named savepoint
			  sp=con.setSavepoint("p1");
			  //query2
				 st.executeUpdate("UPDATE STUDENT SET SADD='hyd'  WHERE SNO=102");
				 //rollback upto savepoint
			con.rollback(sp);
			//Commit the Tx
		con.commit();
		//here query1 will be committed and query will be rolledback
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
				 if(con!=null)
					 con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}

		}//finally
	}//main
}//class
