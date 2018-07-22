package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CheckInputsFilter implements Filter {
	
	static{
		System.out.println("CheckInputsFilter:static block");
	}
	
	public CheckInputsFilter() {
		System.out.println("CheckInputsFilter:0-param constructor");
	
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
         System.out.println("CheckInputs:init(-) method");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter pw=null;
		int val1=0,val2=0;
		System.out.println("CheckInputsFilter:doFilter(-,-,-)");
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		val1=Integer.parseInt(req.getParameter("t1"));
		val2=Integer.parseInt(req.getParameter("t2"));
		//checkinputs (Filter logic)
		if(val1<=0 || val2<=0){
			pw.println("<h1 style='color:red'>Inputs must be +ve </h1>");
		}
		else{
			System.out.println("CheckInputs::before chain.doFilter(-,-)");
			chain.doFilter(req,res);
			System.out.println("CheckInputs::after chain.doFilter(-,-)");
		}
	}//doFilter(-,-)
	
	@Override
	public void destroy() {
		System.out.println("CheckInputsFilter:destroy()");
	
	}//method
}//class
