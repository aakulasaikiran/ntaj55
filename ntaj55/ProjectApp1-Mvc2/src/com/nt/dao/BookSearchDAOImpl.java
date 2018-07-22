package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.BookBO;

public class BookSearchDAOImpl implements BookSearchDAO {
	private static final  String  SELECT_BOOKS_BY_CATEGORY="SELECT BOOKID,BOOKNAME,AUTHORNAME,STATUS,CATEGORY FROM SELECT_BOOKS WHERE CATEGORY=?";

	@Override
	public List<BookBO> findBooks(String category) throws Exception {
		 Connection con=null;
		 PreparedStatement ps=null;
		 List<BookBO> listBO=null;
		 ResultSet rs=null;
		 BookBO bo=null;
		//get JDBC con obj from jdbc con pool
		 con=makeConnection();
		 //create PreparedStatement object
		 ps=con.prepareStatement(SELECT_BOOKS_BY_CATEGORY);
		 //set values to Query params
		 ps.setString(1,category);
		 //execute the Query
		 rs=ps.executeQuery();
		 //copy the records ResultSet object ot ListBO
		 listBO=new ArrayList();
		 while(rs.next()){
			 bo=new BookBO();
			 bo.setBookId(rs.getInt(1));
			 bo.setBookName(rs.getString(2));
			 bo.setAuthor(rs.getString(3));
			 bo.setCategory(rs.getString(5));
			 bo.setStatus(rs.getString(4));
			 listBO.add(bo);
		 }//while
		return listBO;
	}
	
	public  Connection makeConnection()throws Exception{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		 //create InitialContext object
		ic=new InitialContext();
		//get Datasource object 
		ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");
		//get con object from jdbc con pool
		con=ds.getConnection();
		return con;
	}//makeConnection()
}//class
