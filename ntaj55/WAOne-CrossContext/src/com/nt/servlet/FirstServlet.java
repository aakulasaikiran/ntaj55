package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int val=0;
		int square=0;
		ServletContext sc1=null,sc2=null;
		RequestDispatcher rd=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data
		val=Integer.parseInt(req.getParameter("t1"));
		//calc square value
		square=val*val;
		pw.println("FirstServlet: Square Value::"+square);
		//perform CrossContext Communication
		  //get ServletContext of current web application(WAOne-CrossContext)
		sc1=getServletContext();
		//get ServletContext if Dest web application(WATwo-CrossContext)
		sc2=sc1.getContext("/WATwo-CrossContext");
		
		//get RD pointing to SecondServlet of WATwo-CrossContext web application
		rd=sc2.getRequestDispatcher("/surl");
		rd.include(req,res);
		
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
