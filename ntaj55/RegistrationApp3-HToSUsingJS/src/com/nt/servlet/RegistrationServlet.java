package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  PrintWriter pw=null;
	  String pass=null;
	  int length=0;
	  boolean isPassAlphaNumeric=false;
	  //general settings
	  pw=res.getWriter();
	  res.setContentType("text/html");
	  //read form data 
	  pass=req.getParameter("pwd");
	  //check wheather password is weak or strong
	  length=pass.length();
	  for(int i=0;i<length;++i){
		  if(!Character.isUpperCase(pass.charAt(i)) &&
				          !Character.isLowerCase(pass.charAt(i))){
			  isPassAlphaNumeric=true;
			  break;
		  }
	  }//for
	  
	  if(length<6 || isPassAlphaNumeric==false){
		  pw.println("<h1 style='color:red'> Weak Password</h1>");
	  }
	  else{
		  pw.println("<h1 style='color:green'> Strong Password</h1>");
	  }
	  //add hyperlink
	  pw.println("<br><a href='register.html'>home</a>");
	  //close stream
	  pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
	}//doPost(-,-)
}//class

