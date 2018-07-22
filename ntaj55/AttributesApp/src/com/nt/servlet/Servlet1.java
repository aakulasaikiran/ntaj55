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

public class Servlet1 extends HttpServlet {
	
  @Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   PrintWriter pw=null;
   RequestDispatcher rd=null;
   HttpSession  ses=null;
   ServletContext sc=null;
	  //general settings
   res.setContentType("text/html");
   //create attributes
   //request attribute
   req.setAttribute("attr1","va1");
   
   //session attribute
   ses=req.getSession();
   ses.setAttribute("attr2","val2");
   
   //Servletcontext attributes
   sc=getServletContext();
   sc.setAttribute("attr3","val3");
   
   //forward request to Servlet2
   rd=req.getRequestDispatcher("/surl");
   rd.forward(req,res);
}//doGet(-,-)
  @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}//doPost(-,-)
}//class
