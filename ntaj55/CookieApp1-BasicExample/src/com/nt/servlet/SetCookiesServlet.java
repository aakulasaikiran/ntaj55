package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookiesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Cookie cookie1=null,cookie2=null;
		Cookie cookie3=null,cookie4=null;
		// general Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//create InMemory cookies
		cookie1=new Cookie("AP","amaravathi");
		cookie2=new Cookie("TS","hyderabad");
		//add cookies to response
		res.addCookie(cookie1); res.addCookie(cookie2);
		//create Persistent cookies
		 cookie3=new Cookie("MH","mumbai");
		 cookie4=new Cookie("BH", "patna");
		 cookie3.setMaxAge(180);
		 cookie4.setMaxAge(180);
		
		 //add cookies to response
		 res.addCookie(cookie3);
		 res.addCookie(cookie4);
		 
		 pw.println("<br> Cookies are added successfully");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}//class
