package com.nt.wrappers;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {
   HttpServletRequest request=null;
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	public String getParameter(String name){
		String paramValue=null;
		paramValue=request.getParameter(name);
		if(name.equalsIgnoreCase("uname")){
			if(paramValue.indexOf("@gmail.com")==-1)
				paramValue=paramValue+"@gmail.com";
		}
		return paramValue;
	}

}
