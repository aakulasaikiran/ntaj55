package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinksServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  PrintWriter pw=null;
		  String link=null;
		  Locale locales[]=Locale.getAvailableLocales();
		  //general settings
		  pw=res.getWriter();
		  res.setContentType("text/html");
		  //read "s1" additonal request param value (to know that hyperlink that is clicked)
		  link=req.getParameter("s1");
		  //Differentiate the logic based on the hyperlink that is clicked
		  if(link.equals("link1")){
			 pw.println("<h1>All languages are </h1>");
			  for(Locale l:locales){
				  pw.println("<br>"+l.getDisplayLanguage());
			  }//for
		  }//if
		  else if(link.equals("link2")){
			  pw.println("<h1>All countries are </h1>");
			  for(Locale l:locales){
				  pw.println("<br>"+l.getDisplayCountry());
			  }//for
		  }//else
		  else{
			  pw.println("<h1> Date and time "+new Date()+"</h1>");
		  }//else
		  //add hyperlins
		  pw.println("<br><a href='links.html'>home</a>");
		  //close stream
		  pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
