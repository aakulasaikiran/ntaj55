package com.nt.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class DoublePostingPreventingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("DoublePostingPreventingFilter:doFilter(-,-)");
		 HttpSession ses=null;
		 HttpServletRequest  request=null;
		 int serverToken=0;
		 int clientToken=0;
		 RequestDispatcher rd=null;
		 //typecasing
		 request=(HttpServletRequest)req;
		 if(request.getMethod().equals("GET")){
			 System.out.println("GET request");
			 //create SEssion
			 ses=request.getSession(true);
			 //generate ServerSide token
			 ses.setAttribute("sToken",new Random().nextInt(1000));
			 chain.doFilter(req,res);
		 }
		 else{
			 //Access Session
			 ses=request.getSession(false);
			 //get ServerToken
			 serverToken=(Integer)ses.getAttribute("sToken");
			 //get ClientToken
			 clientToken=Integer.parseInt(request.getParameter("cToken"));
			 System.out.println(serverToken+"......"+clientToken);
			 if(serverToken==clientToken){
				 //change ServerToken value
				 ses.setAttribute("sToken",new Random().nextInt(1000));
				 chain.doFilter(req,res);
			 }
			 else{
				 rd=req.getRequestDispatcher("/err.html");
				 rd.forward(req,res);
			 }//else
		 }//else
		 }//doFilter(-,-)
}//class
	
