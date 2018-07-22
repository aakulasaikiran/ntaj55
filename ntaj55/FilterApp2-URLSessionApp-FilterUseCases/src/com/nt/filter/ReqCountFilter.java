package com.nt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ReqCountFilter implements Filter {
	private int count;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	  count=0;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		ServletContext sc=null;
		//count requests
		count++;
		//getServletContext
		sc=req.getServletContext();
		sc.setAttribute("counter",count);
		//chain request to next comp
		System.out.println("RCF:before chain.doFilter(-,-)");
		chain.doFilter(req,res);
		System.out.println("RCF:after chain.doFilter(-,-)");
	}//doFilter(-,-)
}//class
