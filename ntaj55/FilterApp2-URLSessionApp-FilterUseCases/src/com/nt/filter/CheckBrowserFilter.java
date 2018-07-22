package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CheckBrowserFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=null;
		String browser =null;
		PrintWriter pw=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//type casting
		request=(HttpServletRequest)req;
		//get Browser type
		browser=request.getHeader("user-agent");
		if(browser.indexOf("Firefox")==-1){
			pw.println("<h1>Rquest must be given from Mozilla FirexFox");
		}
		else{
			System.out.println("BCF:before chain.doFilter(-,-)");
			chain.doFilter(req,res);
			System.out.println("BCF:after chain.doFilter(-,-)");
		}
	}//doFilter(-,-)

}
