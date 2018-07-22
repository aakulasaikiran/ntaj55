package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerurl")
public class RegisterServlet extends HttpServlet {
	int cnt=0;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null;
		 int age=0;
		//general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read & display form data
		 name=req.getParameter("name");
		 age=Integer.parseInt(req.getParameter("age"));
		 //display form data
		 pw.println("<br> form1/req1 data::"+name+"...."+age);
		 cnt++;
		 pw.println("<br> Request Count ::"+cnt);
		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doGet(req,res);
	}//doPost(-,-)
}//class
