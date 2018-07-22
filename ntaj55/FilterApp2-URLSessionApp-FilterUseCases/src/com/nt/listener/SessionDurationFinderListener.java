package com.nt.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionDurationFinderListener implements HttpSessionListener {
	private long start,end;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	   start=System.currentTimeMillis();
	   ServletContext sc=null;
	   //get ServletContext obj and log messages
	   sc=se.getSession().getServletContext();
	   sc.log(sc.getContextPath()+"--->Session is started at "+new Date());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc=null;
	   end=System.currentTimeMillis();
	   //get ServletContext obj and log messages
	   sc=se.getSession().getServletContext();
	   sc.log(sc.getContextPath()+"--->Session is ended at "+new Date());
	   sc.log(sc.getContextPath()+"---->Session Duration is:::"+(end-start)+" ms");
	}

}
