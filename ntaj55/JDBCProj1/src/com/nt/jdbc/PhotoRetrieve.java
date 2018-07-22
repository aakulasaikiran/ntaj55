package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetrieve {
 private static final String  GET_EMPDETAILS_BY_NO="SELECT ENO,ENAME,ESALARY,EPHOTO FROM EMPALL WHERE ENO=?"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection  con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InputStream is=null;
		OutputStream os=null;
		byte[] buffer=null;
		int bytesRead=0;
		boolean flag=false;
	try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter employee no");
			no=sc.nextInt();
		}
		//register jdbc driver s/w
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		  //create PreparedStaetment obj
		  if(con!=null)
			  ps=con.prepareStatement(GET_EMPDETAILS_BY_NO);
		  //set param values to Query params
		  if(ps!=null)
			  ps.setInt(1,no);
		  //execute the SQL Query in DB s/w
		  if(ps!=null)
			  rs=ps.executeQuery();
		  // get InputStream pointing to Result obj record image
		  if(rs!=null){
			  if(rs.next()){
				  flag=true;
			    is=rs.getBinaryStream(4);
			  }//if
		  }//if
		  if(flag==false){
			  System.out.println("Record not found");
			  return;
		  }
		  //create Output Stream pointing to Dest file
		  os=new FileOutputStream("d:\\gs1.png");
		  //write buffer based logic to complete this operation
		  if(is!=null && os!=null){
		  buffer=new byte[4096];
		  while((bytesRead=is.read(buffer))!=-1){
			  os.write(buffer,0,bytesRead);
		   }
		  }//if
		  System.out.println("File has been copied");
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
			if(rs!=null)
				rs.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
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
		
		try{
			if(is!=null)
				is.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			if(os!=null)
				os.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//finally
}//main
}//class
