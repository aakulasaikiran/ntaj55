package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBatchTest {
  private static final String QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result[]=null;
		int sum=0;
		try{
			//register jdbc driver s/w
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			  //create PreparedStatement obj
			  if(con!=null)
				  ps=con.prepareStatement(QUERY);
			  //set multiple sets of query params to batch
			  if(ps!=null){
				  //1st set of values to batch
				  ps.setInt(1,101); ps.setString(2,"Raja"); ps.setString(3,"hyd");
				  ps.addBatch();
				//2nd set of values to batch
				  ps.setInt(1,102); ps.setString(2,"ramesh"); ps.setString(3,"vizag");
				  ps.addBatch();
			  }
			  //excute the batch
			  if(ps!=null)
				  result=ps.executeBatch();
			  //process the results
			  for(int i=0;i<result.length;++i){
				  sum=sum+result[i];
			  }
			  System.out.println("no.of records that are effected"+sum);
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
				if(ps!=null)
					ps.close();
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
