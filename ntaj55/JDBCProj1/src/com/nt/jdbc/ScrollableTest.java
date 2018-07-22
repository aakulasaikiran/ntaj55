package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTest {
	private static final String QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			//create JDBC Statement object
			/*if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                                         ResultSet.CONCUR_UPDATABLE);*/
			/*  if(con!=null)
				  st=con.createStatement(1005,1008);*/
			if(con!=null)
				ps=con.prepareStatement(QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,
						                                                             ResultSet.CONCUR_UPDATABLE);
			  
			//create ResultSet obj
			if(ps!=null)
				rs=ps.executeQuery();
			//Process ReultSet(Display records (top-botton))
			System.out.println("Records (top-bottom)");
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3));
				}//while
			}//if
			System.out.println("Records (bottom-top)");
			rs.afterLast();
			if(rs!=null){
				while(rs.previous()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"   "+rs.getString(3));
				}
			}//if
			//move the cursor in ResultSet randomly
		if(rs!=null){
			rs.first();
			System.out.println(rs.getRow()+"--->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			rs.last();
			System.out.println(rs.getRow()+"--->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			rs.absolute(4);
			System.out.println(rs.getRow()+"--->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			rs.absolute(-3);
			System.out.println(rs.getRow()+"--->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			rs.relative(2);
			System.out.println(rs.getRow()+"--->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
			rs.relative(-4);
			System.out.println(rs.getRow()+"--->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
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
			try{
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally 
	}//main
}//class
