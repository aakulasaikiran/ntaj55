package com.nt.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationMaintenceListener implements ServletContextListener {
    long start,end;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		start=System.currentTimeMillis();
		ServletContext sc=null;
		//get Servletcontext obj and write log messages
		sc=sce.getServletContext();
		sc.log(sc.getContextPath()+" is deployed at::"+new Date());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext sc=null;
		end=System.currentTimeMillis();
		//get Servletcontext obj and write log messages
		sc=sce.getServletContext();
		sc.log(sc.getContextPath()+" is undeployed at::"+new Date());
		sc.log(sc.getContextPath()+" is running mode continuosly for "+(end-start)+" ms.");
	}
	
}//class
