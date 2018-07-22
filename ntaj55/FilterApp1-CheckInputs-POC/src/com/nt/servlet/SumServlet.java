package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SumServlet extends HttpServlet {
	
	static{
		System.out.println("SumServlet:static block");
	}
	
	public SumServlet() {
	  System.out.println("SumServlet:0-param constructor");
	}
	
	@Override
	public void init() throws ServletException {
	   System.out.println("SumServlet:init() method");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("SumServlet:doGet(-,-)");
		 PrintWriter pw=null;
		 int sum=0;
		  //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form data
		 int val1=Integer.parseInt(req.getParameter("t1"));
		 int val2=Integer.parseInt(req.getParameter("t2"));
		 //find sum
		 sum=val1+val2;
		 //Display results
		 pw.println("<h1> Sum is:::"+sum+"</h1>");
		 //add hyperlink
		 pw.println("<a href='form.html'>home</a>");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
	}//doPost(-,-)
	
	@Override
	public void destroy() {
	  System.out.println("SumServlet:destroy() method");
	}
	
}//class

