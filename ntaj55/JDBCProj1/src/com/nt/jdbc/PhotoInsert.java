package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoInsert {
  private static final String  INSERT_EMPALL="INSERT INTO EMPALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null;
		float salary=0.0f;
		String photoPath=null;
		Connection con=null;
		PreparedStatement ps=null;
		InputStream is=null;
		File file=null;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter emp no::");
				no=sc.nextInt(); //gives 101
				System.out.println("Enter emp name::");
				name=sc.next(); //gives raja
				System.out.println("Enter emp salary::");
				salary=sc.nextFloat(); //gives 9000.4;
				System.out.println("Enter emp photo file path::");
				photoPath=sc.next(); //gives c:\\users\\nareshit\\Desktop\\gs.png
			}
			/*	//register jdbc driver s/w
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");*/
			
			//register jdbc driver s/w
			   Class.forName("com.mysql.jdbc.Driver");
			   //establish the connection
			  con=DriverManager.getConnection("jdbc:mysql:///ntaj55db1", "root","root");
			
			  //create PreparedStatement object
				if(con!=null)
					ps=con.prepareStatement(INSERT_EMPALL);
				//get InputStream representing the file to be inserted
				file=new File(photoPath);
				is=new FileInputStream(file);
				//set values to Query params
				if(ps!=null){
					ps.setInt(1,no);
					ps.setString(2,name);
					ps.setFloat(3,salary);
					ps.setBinaryStream(4,is,(int)file.length());
				}//if
				//execute the Query
				if(ps!=null)
					result=ps.executeUpdate();
				
				if(result==0)
					System.out.println("Record not inserted");
				else
					System.out.println("record inserted");
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
