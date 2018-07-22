package com.nt.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 String ss=null;
		 String engine=null;
		 String url=null;
		//read form data
		 ss=req.getParameter("ss").replace(' ','+');
		engine=req.getParameter("engine");
		//prepare url for SendRedirection based on Search Engine that is selected
		if(engine.equals("google")){
			url="https://www.google.co.in/search?q="+ss;
		}
		else if(engine.equals("bing")){
			url="http://www.bing.com/search?q="+ss;
		}
		else{
			url="https://in.search.yahoo.com/search?p="+ss;
		}
			
		//perform SendRedirection
		System.out.println("SearchServlet:before sendRd(-)");
		res.sendRedirect(url);
		RequestDispatcher rd=req.getRequestDispatcher("/error.html");
		rd.include(req,res);
		System.out.println("SearchServlet:after sendRd(-)");
		
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class

