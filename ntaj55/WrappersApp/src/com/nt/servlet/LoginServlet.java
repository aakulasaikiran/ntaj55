package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginurl")
public class LoginServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String un=null,pass=null;
		  //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form data
		 un=req.getParameter("uname");
		 pass=req.getParameter("pwd");
		 //write b.logic /request processing logic
		 if(un.equals("raja@gmail.com") && pass.equals("rani"))
			 pw.println(" <h1 style='center'>Valid Credentials</h1>");
		 else
			 pw.println(" <h1 style='center'>InValid Credentials</h1>");
		 //add hyperlink
		 pw.println("<a href='login.html'>home</a>");
		 //close stream
		 pw.close();
	}//doPost(-,-)
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doPost(req,res);
	}//doGet(-,-)
}//class
