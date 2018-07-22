package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
	public TestServlet() {

	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		   //general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read and display context param values
		ServletContext sc=getServletContext();
		pw.println("<br> dbuser context parameter value"+sc.getInitParameter("dbuser"));
		pw.println("<br> Servletcontext obj class name"+sc.getClass());
		
		pw.println("<br> Server info"+sc.getServerInfo());
		  //gives  Tomcat 9.0 
		pw.println("<br> Servlet api version"+sc.getMajorVersion()+
				                                                                       "."+sc.getMinorVersion());
		 //4.0
		pw.println("<br> Context path of web applicaiton"+sc.getContextPath());
		// DBApp-contextparams
		pw.println("<br> WebRoot folder path"+sc.getRealPath("/"));
		 // D:\Tomcat 9.0\webapps\DBApp-contextparams
		pw.println("<br> input.html file path"+sc.getRealPath("/input.html"));
		 //D:\Tomcat 9.0\webapps\DBApp-contextparams\input.html
		pw.println("<br> MIME type of input.html"+sc.getMimeType("input.html"));
		  //  text/html
		sc.log("Sys date"+new Date());
		 //writes the message to <Tomcat_home>\logs\localhost-2016-10-21.txt
		
		//read and display init param value
		ServletConfig cg=getServletConfig();
		pw.println("<br> dbuser init parameter value"+cg.getInitParameter("p1"));
		
		
		
		
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
