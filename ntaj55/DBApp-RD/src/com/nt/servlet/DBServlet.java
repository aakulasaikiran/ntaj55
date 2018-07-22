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
		ServletConfig cg=null;
		String driver=null,url=null,dbuser=null,dbpwd=null;
		try{
			//access ServletConfig obj
			cg=getServletConfig();
			//read jdbc properties from init params
			driver=cg.getInitParameter("driver");
			url=cg.getInitParameter("url");
			dbuser=cg.getInitParameter("dbuser");
			dbpwd=cg.getInitParameter("dbpwd");
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
		RequestDispatcher rd=null,rd1=null,rd2=null;
		// general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		try{
			//include header
			rd1=req.getRequestDispatcher("/headurl");
			rd1.include(req,res);
			
		//read form data
		no=Integer.parseInt(req.getParameter("eno"));
		//write jdbc code
		
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
			pw.println("<br><br><br><br>");
			//include footer
			rd2=req.getRequestDispatcher("/footer.html");
			pw.close();
			rd2.include(req,res);
			//close stream
			pw.close();
		}//try
		catch(Exception e){
			//forward request to ErrorServlet
			pw.println("<b>before rd.forward(-,-)</b>");
			rd=req.getRequestDispatcher("eurl");
			rd.forward(req, res);
			pw.println("<b>after rd.forward(-,-)</b>");
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
