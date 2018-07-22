package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int val=0;
		int cube=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		val=Integer.parseInt(req.getParameter("t1"));
		//calc cube value
		cube=val*val*val;
		//display cube value
		pw.println("<br>(SecondServlet)Cube Value :::"+cube);
		//do not close stream
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
