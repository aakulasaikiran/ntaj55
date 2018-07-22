package com.nt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.BookDTO;
import com.nt.service.BookSearchService;
import com.nt.service.BookSearchServiceImpl;

public class BookSearchController extends HttpServlet {

	 @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 String category=null;
		 String source=null;
		 BookSearchService service=null;
		 RequestDispatcher rd=null;
		 List<BookDTO> listDTO=null;
		 // read form data
		 category=req.getParameter("category");
		 source=req.getParameter("source");
		 try{
		 //use Service class
		 service=new BookSearchServiceImpl();
		 listDTO=service.search(category);
		 //keep listDTO in request scope
		 req.setAttribute("booksDTO",listDTO);
		 //forward to ExcelScreen.jsp or HtmlScreen.jsp based on source button
		 if(source.equals("html")){
			 rd=req.getRequestDispatcher("/HtmlScreen.jsp");
			 rd.forward(req,res);
		 }
		 else{
			 rd=req.getRequestDispatcher("/ExcelScreen.jsp");
			 rd.forward(req,res);
		   }
		 }
		 catch(Exception se){
			 se.printStackTrace();
			 rd=req.getRequestDispatcher("/error.jsp");
			 rd.forward(req,res);
		 }
	}//doGet(-,-)
	 
	 @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}//doPost(-,-)
}//class
