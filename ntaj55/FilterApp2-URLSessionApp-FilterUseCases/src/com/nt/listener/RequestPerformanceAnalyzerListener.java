package com.nt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestPerformanceAnalyzerListener implements ServletRequestListener {
    private long start,end;
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
	  start=System.currentTimeMillis();
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	  end=System.currentTimeMillis();
	  HttpServletRequest req=null;
	  ServletContext sc=null;
	  //get request object
	  req=(HttpServletRequest)sre.getServletRequest();
	  // get Servletcontext obj
	  sc=req.getServletContext();
	  //write log messages
	  sc.log("request processing time for "+req.getRequestURI()+" is "+(end-start)+"ms");
	}
}
