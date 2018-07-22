//DBServlet.java
package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DBServlet extends HttpServlet
{
	private static final String GET_EMP_DETAILS_BY_NO="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=?";
	private Connection con;
    private PreparedStatement ps;
	private ResultSet rs;

	public void init(){
		ServletContext sc=null;
		String driver=null,url=null,dbuser=null,dbpwd=null;
		try{
			//access ServletConfig obj
			sc=getServletContext();
			//read jdbc properties from init params
			driver=sc.getInitParameter("driver");
			url=sc.getInitParameter("url");
			dbuser=sc.getInitParameter("dbuser");
			dbpwd=sc.getInitParameter("dbpwd");
			System.out.println(driver+"  "+url+"   "+dbuser+"   "+dbpwd);
          //register driver
		  Class.forName(driver);
		  //establish the connection
		  con=DriverManager.getConnection(url,dbuser,dbpwd);
           //create PrpearedStatement object
		   ps=con.prepareStatement(GET_EMP_DETAILS_BY_NO);
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
	}//init()

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{   int no=0;
		PrintWriter pw=null;
		// general settings
		pw=res.getWriter();
		
		res.setContentType("application/ms-word");
		res.addHeader("Content-Disposition","attachment;fileName=Title1.doc");
		
		//read form data
		no=Integer.parseInt(req.getParameter("eno"));
		//write jdbc code
		try{
			//set value to Query parameter
			ps.setInt(1,no);
			//execute the Query
            rs=ps.executeQuery();
			//process the ResultSet
			if(rs.next()){
				pw.println("<h1><center> Emp Details </h1></center>");
				pw.println("<br> Emp no::: "+rs.getInt(1));
				pw.println("<br> Emp name::: "+rs.getString(2));
				pw.println("<br> Emp JOB::: "+rs.getString(3));
				pw.println("<br> Emp Salary :::"+rs.getString(4));
			}
			else{
				pw.println("<h1><center>Emp Details not found</center></h1>");
			}
			//add hyperlink
			pw.println("<a href='input.html'>home</a>");
			ServletConfig cg=getServletConfig();
			pw.println("<br> Servlet logical name"+cg.getServletName());
			pw.println("<br> class name of ServletConfig obj"+cg.getClass());
			//close stream
			pw.close();
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//doGet(-,-)
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{ 
		doGet(req,res);
	}//doPost(-,-)

	public void destroy(){
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
	}//destroy()
}//class
