package com.nt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PerformanceTestFilter implements Filter{
	private long start=0;
	private long end=0;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		ServletContext sc=null;
		start=System.currentTimeMillis();
		chain.doFilter(req,res);
		end=System.currentTimeMillis();
		//write log message
		sc=req.getServletContext();
		sc.log( ((HttpServletRequest)req).getRequestURI()+" has taken "+(end-start)+"  ms.");
	}//doFilter(-,-)
	

}
