package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=null,address=null;
        int	age=0;
        HttpSession ses=null;
		
        PrintWriter pw=res.getWriter();
		
	   //general settings
		 res.setContentType("text/html");
		 //read form1/req1 data
		 name=req.getParameter("name");
		 address=req.getParameter("address");
		 age=Integer.parseInt(req.getParameter("age"));
		 //create or locate HttpSession object
		 ses=req.getSession(true);
		 //keep form1/req1 data in HttpSession obj as session attribute values
		 ses.setAttribute("name",name);
		 ses.setAttribute("addrs",address);
		 ses.setAttribute("age",age);
		 
		 ses.setMaxInactiveInterval(60);
		 //Generate Form2 dynamically from here
		 pw.println("<h1 style='text-align:center'> Form2-Provide Skill Set </h1>");
		 pw.println("<form action='surl' method='post'>");
		 pw.println("<table>");
		 pw.println("<tr><td>Select Skill :</td>");
		 pw.println("<td><select name='skill' >");
		 pw.println("<option value='java'>JAVA/JEE</option>");
		 pw.println("<option value='.net'>.NET</option>");
		 pw.println("<option value='php'>PHP</option>");
		 pw.println("<option value='dba'>DBA</option>");
		 pw.println("</select></td></tr>");
		 pw.println("<tr><td> Experience:: </td>");
		 pw.println("<td><input type='text' name='exp'></td></tr>");
		 pw.println("<tr><td colspan='2'><input type='submit' value='continue'></td></tr>");
		 pw.println("</table>");
		 pw.println("</form>");
		 pw.println("<br> Session Id:: "+ses.getId());
		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}
}//class
