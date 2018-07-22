package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsert {
	private static final String INSERT_STUDENT="INSERT INTO STUDENTALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null;
		String addrs=null;
		String resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		FileReader reader=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student no::");
				no=sc.nextInt();
				System.out.println("Enter student name:::");
				name=sc.next();
				System.out.println("enter student address:::");
				addrs=sc.next();
				System.out.println("Enter resume file path::");
				resumePath=sc.next();
			}
			
			//register jdbc driver s/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			  //create PreparedStaement
			  if(con!=null)
				  ps=con.prepareStatement(INSERT_STUDENT);
			  //create File,FileReader objs
			  file=new File(resumePath);
			  reader=new FileReader(file);
			  //set values to query params
			  if(ps!=null){
				  ps.setInt(1, no);
				  ps.setString(2,name);
				  ps.setString(3,addrs);
				  ps.setCharacterStream(4, reader, file.length());
			  }//if
			  //execute the Query
			  if(ps!=null){
				  result=ps.executeUpdate();
			  }
			  if(result==0)
				  System.out.println("Record not inserted");
			  else
				  System.out.println("Record inserted");
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
