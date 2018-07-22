package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {
	
  @Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   PrintWriter pw=null;
   RequestDispatcher rd=null;
   ServletContext sc=null;
	  //general settings
   res.setContentType("text/html");
   pw=res.getWriter();
   //read and display attribute values
   //request attribute
   pw.println("<br>Servlet2:::request attribute(attr1) value:::"+req.getAttribute("attr1"));
   //session attribute
   HttpSession ses=req.getSession();
   pw.println("<br>Servlet2:::Session attribute(attr2) value:::"+ses.getAttribute("attr2"));
   //ServletContext attributes
   sc=getServletContext();
   pw.println("<br> Servlet2::: Servlet Context attribute(attr3) value:::"+sc.getAttribute("attr3"));
   
   
   //forward request to Servlet3
   rd=req.getRequestDispatcher("/turl");
   rd.forward(req,res);
}//doGet(-,-)
  @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}//doPost(-,-)
}//class
