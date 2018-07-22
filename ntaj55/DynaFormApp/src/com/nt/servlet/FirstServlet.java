package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,fname=null;
		String ms=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/request1 data
		name=req.getParameter("name");
		fname=req.getParameter("fname");
		ms=req.getParameter("ms");
		//handle not selected state of checbox (ms)
		if(ms==null)
			ms="single";
		//Generate form2 dynamically based on  marital status
		if(ms.equalsIgnoreCase("married")){
			pw.println("<h1 style='text-align:center'> Dyanamic Form2</h1>");
			pw.println("<form action='surl' method='post'>");
			pw.println("Spouse name::<input type='text' name='f2t1'><br>");
			pw.println("No.of children::<input type='text' name='f2t2'><br>");
			pw.println("<input type='submit' value='submit'>");
			pw.println("</form>");
		}
		else{
			pw.println("<h1 style='text-align:center'> Dyanamic Form2</h1>");
			pw.println("<form action='surl' method='post'>");
			pw.println("When do u want to marry::<input type='text' name='f2t1'><br>");
			pw.println("why do u want to marry::<input type='text' name='f2t2'><br>");
			pw.println("<input type='submit' value='submit'>");
			pw.println("</form>");
		}
		//close stream 
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)
}//class

