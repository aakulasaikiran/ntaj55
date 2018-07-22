package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {
  private static final String QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			//create Datasource obj
			ds=new OracleConnectionPoolDataSource();
			
			//set Properties to create con objs in jdbc con pool
			if(ds!=null){
			ds.setDriverType("thin");
			ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ds.setUser("system");
			ds.setPassword("manager");
			}
			//get con obj from jdbc con pool
			if(ds!=null){
			  con=ds.getConnection();
			}
			//write jdbc code having other jdbc objs
			if(con!=null){
				st=con.createStatement();
			}
			if(st!=null){
				rs=st.executeQuery(QUERY);
			}
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc,stream objs
			try{
			  if(st!=null)
				  st.close();
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
		}//finally
	}//main
}//class
