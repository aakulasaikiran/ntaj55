package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;

public class UploadServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{	PrintWriter pw=null;
	   MultipartFormDataRequest nreq=null;
	   UploadBean uploadBean=null;
	   Hashtable<String,UploadFile> uplFiles=null;
	   Enumeration e=null;
	   UploadFile file=null;
		//general settings
	pw=res.getWriter();
	res.setContentType("text/html");
	
	try{
		  //read uploaded files
	 nreq=new MultipartFormDataRequest(req);
	 //set file uploading settings
	 uploadBean=new UploadBean();
	 uploadBean.setOverwrite(false);
	 uploadBean.setFilesizelimit(5*1024);
	 uploadBean.setFolderstore("c:/store");
	 //complete file uploading
	 uploadBean.store(nreq);
	 // get all the uploaded files
	  uplFiles=nreq.getFiles();
	  //display the names of the uploaded files
	  pw.println("<h1> The Uploaded files are </h1>");
	  e=uplFiles.elements();
	  while(e.hasMoreElements()){
		  file=(UploadFile)e.nextElement();
		  pw.println("<br>"+file.getFileName()+"...."+file.getFileSize()+" ..."+file.getContentType());
	  }//while
	}//try
    catch (Exception exp) {
	 exp.printStackTrace();
	}
	  //close stream
	  pw.close();
 }//doGet(-,-)
 @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
   doPost(req,res);
  }//doPost(-,-)
}//class
