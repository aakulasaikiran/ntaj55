package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class FlexibleTest {
	private static final String QUERY="SELECT * FROM STUDENT";

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			//locate properties file
			is=new FileInputStream("src/com/nt/commons/DBDetails.properties");
			//load the properties file content to java.util.Properties class obj
			props=new Properties();
			props.load(is);
			//write jdbc code
			   //register jdbc driver s/w
			    Class.forName(props.getProperty("jdbc.driver"));
			   //Establish the connection
			    con=DriverManager.getConnection(props.getProperty("jdbc.url"),
			    		                                                              props.getProperty("jdbc.user"),
			    		                                                              props.getProperty("jdbc.pwd"));
			    //create STatement obj
			    if(con!=null)
			    	st=con.createStatement();
			    //send and execute SQL Query in DB s/w
			    if(st!=null)
			    	rs=st.executeQuery(QUERY);
			    //process the ResultSet
			    if(rs!=null){
			      while(rs.next()){
			        System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));	
			     }//while
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
				//close jdbc objs
				try{
					 if(rs!=null)
						 rs.close();
					}
					catch(SQLException se){
						se.printStackTrace();
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
