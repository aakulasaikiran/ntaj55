package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String comp=null;
		 int val1=0,val2=0;
		  //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read "s1" request param value
		 comp=req.getParameter("s1");
		 //write request processing logic
		 if(comp.equalsIgnoreCase("add")){
			 //read form data
			 val1=Integer.parseInt(req.getParameter("t1"));
			 val2=Integer.parseInt(req.getParameter("t2"));
			 pw.println("<h1>Add===>"+(val1+val2)+"</h1>");
		 }
		 else if(comp.equalsIgnoreCase("sub")){
			 //read form data
			 val1=Integer.parseInt(req.getParameter("t1"));
			 val2=Integer.parseInt(req.getParameter("t2"));
			 pw.println("<h1>Sub===>"+(val1-val2)+"</h1>");
		 }
		 else if(comp.equalsIgnoreCase("mul")){
			 //read form data
			 val1=Integer.parseInt(req.getParameter("t1"));
			 val2=Integer.parseInt(req.getParameter("t2"));
			 pw.println("<h1>Mul===>"+(val1*val2)+"</h1>");
		 }
		 else if(comp.equalsIgnoreCase("link1")){
			 pw.println("<br> <h1>System Date is"+new Date()+"</h1>");
		 }
		 else{
			 pw.println("<br><h1> System Properties"+System.getProperties()+"</h1>");
		 }//else
		 //add hyperlink
		 pw.println("<a href='form.html'>home</a>");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
