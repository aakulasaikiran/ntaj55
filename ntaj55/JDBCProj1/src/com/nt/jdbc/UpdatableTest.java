package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableTest {
 private static final String QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		try{
			//register jdbc driver s/w
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                                         ResultSet.CONCUR_UPDATABLE);
			//create ResultSet
			if(st!=null)
				rs=st.executeQuery(QUERY);
			//select operation
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
				}//while
			}//if
			/*//insert operation
			if(rs!=null){
			 rs.moveToInsertRow(); //creates an empty record in ResultSet
			  rs.updateInt(1,300);
			  rs.updateString(2,"raja");
			  rs.updateString(3,"hyd");
			   rs.insertRow();
			   System.out.println("insert operation completed");
			}
			*/
		/*	//update operation
			if(rs!=null){
				rs.absolute(4);
				rs.updateString(3,"new hyd");
				rs.updateRow();
				System.out.println("update operation completed");
			}*/
			//delete operation
			if(rs!=null){
				rs.absolute(4);
				rs.deleteRow();
				System.out.println("delete operation completed");
			}
			
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
			catch(Exception e){
				e.printStackTrace();
			}

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