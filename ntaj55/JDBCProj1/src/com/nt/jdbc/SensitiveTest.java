package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveTest {
 private static final String QUERY="SELECT SNO,SNAME,SADD FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		try{
			//register jdbc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						                                         ResultSet.CONCUR_UPDATABLE);
			//create ResultSet
			if(st!=null)
				rs=st.executeQuery(QUERY);
			while(rs.next()){
				if(cnt==1)
					Thread.sleep(30000);  //modify db table records from SQL prompt
				rs.refreshRow();
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                 cnt++;
			}//while
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
