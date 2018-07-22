package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null,gender=null,addrs=null,ms=null,qlfy=null,hobies[]=null,courses[]=null;
		 int age=0;
		//General Settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form data
		 name=req.getParameter("pname");
		 age=Integer.parseInt(req.getParameter("page"));
		 addrs=req.getParameter("paddrs");
		 gender=req.getParameter("gender");
		 ms=req.getParameter("ms");
		 if(ms==null)
			 ms="single";
		 qlfy=req.getParameter("qlfy");
		 courses=req.getParameterValues("courses");
		 if(courses==null){
			 courses=new String[1];
			 courses[0]="No Course is selected";
		 }
		 hobies=req.getParameterValues("hb");
		 if(hobies==null){
			 hobies=new String[1];
			 hobies[0]="No hobie is selected";
		 }
		 //write b.logic or request processing logic
		 if(gender.equalsIgnoreCase("M")){
			 if(age<=5)
				 pw.println("<h1>Master."+name+" u r baby boy</h1>");
			 else if(age<=12)
				 pw.println("<h1>Master."+name+" u r small boy</h1>");
			 else if(age<=19)
				 pw.println("<h1>Master."+name+" u r teenage boy</h1>");
			 else if(age<=35)
				 pw.println("<h1>Mr."+name+" u r young man</h1>");
			 else if(age<=60)
				 pw.println("<h1>Mr."+name+" u r middle-aged man</h1>");
			 else 
				 pw.println("<h1>Mr."+name+" u r budda man</h1>");
		 }//if
		 else{
			 if(age<=5)
				 pw.println("<h1>Master."+name+" u r baby girl</h1>");
			 else if(age<=12)
				 pw.println("<h1>Master."+name+" u r small girl</h1>");
			 else if(age<=19)
				 pw.println("<h1>Master."+name+" u r teenage girl</h1>");
			 else if(age<=35){
				 if(ms.equals("married")){
				    pw.println("<h1>Mrs."+name+" u r young woman</h1>");
				 }
				 else{
					 pw.println("<h1>Miss."+name+" u r young woman</h1>");
				 }
			 }
			 else if(age<=60){
				 if(ms.equals("married")){
					    pw.println("<h1>Mrs."+name+" u r middle-aged woman</h1>");
					 }
					 else{
						 pw.println("<h1>Miss."+name+" u r middle-aged woman</h1>");
					 }
			 }
			 else {
				 if(ms.equals("married")){
					    pw.println("<h1>Mrs."+name+" u r old woman</h1>");
					 }
					 else{
						 pw.println("<h1>Miss."+name+" u r old woman</h1>");
					 }
			 }//else
		 }//else
		 //Display form data
		 pw.println("<br><br><h1> Form data is</h1> <br><br>");
		 pw.println("<br>name===>"+name);
		 pw.println("<br>age===>"+age);
		 pw.println("<br>Marital Status===>"+ms);
		 pw.println("<br>gender===>"+gender);
		 pw.println("<br>address===>"+addrs);
		 pw.println("<br>qualification===>"+qlfy);
		 pw.println("<br>Courser===>"+Arrays.toString(courses));
		 pw.println("<br>Hobies===>"+Arrays.toString(hobies));
		 //add hyperlink
		 pw.println("<a href='form.html'>home</a>");
		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
	}

}//class
