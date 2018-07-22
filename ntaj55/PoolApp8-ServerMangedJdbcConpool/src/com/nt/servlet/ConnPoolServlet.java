package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ConnPoolServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String tabName=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		tabName=req.getParameter("table");
		//write jdbc code
		try{
			//get Connection obj from jdbc con pool
			con=makeConnection();
			//create Statement obj
			st=con.createStatement();
			//send and execute SQL Query in DB s/w
			rs=st.executeQuery("SELECT * FROM   "+tabName);
		    //display col names
			rsmd=rs.getMetaData();
			pw.println("<table border='1'>");
			pw.println("<tr bgcolor='red'>");
			for(int i=1;i<=rsmd.getColumnCount();++i){
				pw.println("<th>"+rsmd.getColumnLabel(i)+"</th>");
			}//for
			pw.println("</tr>");
			//display col values
			while(rs.next()){
				pw.println("<tr>");
				for(int i=1;i<=rsmd.getColumnCount();++i){
					pw.println("<td>"+rs.getString(i)+"</td>");
				}//for
				pw.println("</tr>");
			}//while
			pw.println("</table>");
			//add hyperlink
			pw.println("<a href='index.html'>home</a>");
		}//try
		catch(SQLException se){
			se.printStackTrace();
			pw.println("<p style='color:red'> DB table not found</p>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
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
							con.close(); //releases the con obj back to jdbc con pool
					}
						catch(SQLException se){
							se.printStackTrace();
						}	
			}//finally
		}//doGet(-,-)
	
	@Override
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doGet(req,res);
	}
	public Connection makeConnection(){
		Connection con=null;
		InitialContext ic=null;
		DataSource ds=null;
		try{
		//get jdbc con obj from jdbc con pool
		 ic=new InitialContext();  //connects to Jndi Registry
		 ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");  //gets DataSource obj ref
		 con=ds.getConnection(); //gets con obj from jdbc con pool
		}//try
		catch(Exception se){
			se.printStackTrace();
		}
		return con;
	}//makeConnectiion()
}//class
