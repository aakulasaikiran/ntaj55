package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCookiesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Cookie cks[]=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read and display cookie names, values
		cks=req.getCookies();
		if(cks!=null){
		//display cookie values
		pw.println("<table border='1'>");
		pw.println("<tr><th>name</th><th>Value</th></tr>");
		for(Cookie ck:cks){
			pw.println("<tr><td>"+ck.getName()+"</td><td>"+ck.getValue()+"</td></tr>");
		}//for
		pw.println("</table>");
		}
		else{
			pw.println("<h1>No Cookies present</h1>");
		}
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)

}//class
