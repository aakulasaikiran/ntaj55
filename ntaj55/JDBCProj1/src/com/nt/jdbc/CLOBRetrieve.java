package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieve {
 private static final String  GET_STUDENTS_BY_NO="SELECT SNO,SNAME,SADD,RESUME FROM STUDENTALL WHERE SNO=?"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection  con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader reader=null;
		Writer writer=null;
		char[] buffer=null;
		int charsRead=0;
		boolean flag=false;
	try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter student  no");
			no=sc.nextInt();
		}
		//register jdbc driver s/w
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
		  //create PreparedStaetment obj
		  if(con!=null)
			  ps=con.prepareStatement(GET_STUDENTS_BY_NO);
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
			    reader=rs.getCharacterStream(4);
			  }//if
		  }//if
		  if(flag==false){
			  System.out.println("Record not found");
			  return;
		  }
		  //create Output Stream pointing to Dest file
		  writer=new FileWriter("d:\\resume1.txt");
		  //write buffer based logic to complete this operation
		  if(reader!=null && writer!=null){
		  buffer=new char[1024];
		  while((charsRead=reader.read(buffer))!=-1){
			  writer.write(buffer,0,charsRead);
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
			if(reader!=null)
				reader.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			if(writer!=null)
				writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//finally
}//main
}//class
