package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.StudentBO;

public class StudentDAO {
	private static final String  STUDENT_INSERT_QUERY="INSERT INTO DAO_STUDENT VALUES(?,?,?,?,?)";
	
	private Connection getConnection(){
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		try{
		//get InitialContext obj
		ic=new InitialContext();
		//get DataSource obj
		ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");
		//get pooled con object
		con=ds.getConnection();
		return con;
		}//try
		catch(Exception e){
			return null;
		}
	}//getConnection();
	
	public int  insert(StudentBO bo){
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try{
			//write jdbc code save record
			  //get con obj from jdbc con pool
			  con=getConnection();
			  //create PrpearedStatement obj
			  ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			  //set values to query params
			  ps.setInt(1, bo.getSno());
			  ps.setString(2,bo.getSname());
			  ps.setInt(3,bo.getTotal());
			  ps.setFloat(4,bo.getAvg());
			  ps.setString(5,bo.getResult());
			  //execute the Query
			  result=ps.executeUpdate();
			  return result;
		}//try
		catch(SQLException se){
			return 0;
		}
		catch(Exception e){
			return 0;
		}
		
	}//method
	
}//clas
