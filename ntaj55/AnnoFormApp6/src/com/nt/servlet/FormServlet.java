package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/formurl")
//@WebServlet(urlPatterns="/formurl",name="form",loadOnStartup=1)
//@WebServlet(value="/formurl",initParams=@WebInitParam(name="dbuser",value="system" ))
@WebServlet(value="/formurl",
                            initParams={@WebInitParam(name="dbuser",value="system" ),
                            		                 @WebInitParam(name="dbpwd",value="tiger" )
                                                   }
                           )
public class FormServlet extends HttpServlet {
	public FormServlet() {
		System.out.println("FormServlet::0-param constructor");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw=null;
		 String name=null;
		  //general settings
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 //read form data
		 name=req.getParameter("tname");
		 //generate wishMsg
		 pw.println("<h1>Good morning:::"+name+"</h1>");
		 //add hyperlink
		 pw.println("<a href='form.html'>home</a>");
		 //read init param values
		 ServletConfig cg=getServletConfig();
		 pw.println("<br>dbuser init param vlaue:::"+cg.getInitParameter("dbuser"));
		 pw.println("<br>dbuser init param vlaue:::"+cg.getInitParameter("dbpwd"));
		 //read context param values
		 ServletContext sc=getServletContext();
		 pw.println("<br> email context param value:::"+sc.getInitParameter("email"));
		 //close stream
		 pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
