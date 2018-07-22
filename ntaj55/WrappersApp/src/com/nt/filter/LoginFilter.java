package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.wrappers.MyRequest;
import com.nt.wrappers.MyResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/loginurl")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		MyRequest custReq=null;
		MyResponse custRes=null;
		HttpServletRequest request=null;
		HttpServletResponse response=null;
		String output=null;
		PrintWriter pw=null;
		//type casting
		request=(HttpServletRequest)req;
		response=(HttpServletResponse)res;
		//create CustomReqest,CustomResponse  Objects
		custReq=new MyRequest(request);
		custRes=new MyResponse(response);
		 chain.doFilter(custReq, custRes);
		 //get output from CustRes
		 output=custRes.toString();
		 //modify output
		 output=output.toLowerCase();
		 //add signature to output
		 output=output+"<br>HCL ,Ameerpet";
		 //writer output to browser 
		 pw=res.getWriter();
		 pw.println(output);
		 //close stream
		 pw.close();
	}//doFilter
	


}
