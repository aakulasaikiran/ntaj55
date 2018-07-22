package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecondServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String skill=null;
        int	exp=0;
        HttpSession ses=null;
		
        PrintWriter pw=res.getWriter();
	   //general settings
		 res.setContentType("text/html");
		 //read form2/req2 data
		 skill=req.getParameter("skill");
		 exp=Integer.parseInt(req.getParameter("exp"));
		 // locate HttpSession object
		 ses=req.getSession(false);
		 //keep form2/req2 data in HttpSession obj as session attribute values
		 ses.setAttribute("skill",skill);
		 ses.setAttribute("exp",exp);
		 //Generate Form3 dynamically from here
		 pw.println("<h1 style='text-align:center'> Form3-Provide Expected Salary and Location </h1>");
		 pw.println("<form action="+res.encodeURL("turl")+" method='post'>");
		 pw.println("<table>");
		 pw.println("<tr><td> Preffered Location :</td>");
		 pw.println("<td><input type='text' name='city'></td></tr>");
		 pw.println("<tr><td> Expected Salary:: </td>");
		 pw.println("<td><input type='text' name='salary'></td></tr>");
		 pw.println("<tr><td colspan='2'><input type='submit' value='submit'></td></tr>");
		 pw.println("</table>");
		 pw.println("</form>");
		 pw.println("<br> Session Id:: "+ses.getId());
		 pw.println("<br><br>Req Count:::"+getServletContext().getAttribute("counter"));

		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}
}//class
