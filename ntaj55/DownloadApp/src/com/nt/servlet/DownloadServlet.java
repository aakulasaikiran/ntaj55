package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 final String FILE_NAME="c:/store/james.png";
		 File file=null; 
		 ServletContext sc=null;
		 String mimeType=null;
		 long length=0;
		 InputStream is=null;
		 ServletOutputStream sos=null;
		  byte[] buffer =null;
		 int  bytesRead=0;
		 //get ServletContext obj
		 sc=getServletContext();
		//locate the file
		file=new File(FILE_NAME);
		//get MIMEType of the file and set it as response content type
		mimeType=sc.getMimeType(FILE_NAME);
		res.setContentType(mimeType);
		//get length of the file and set it as  response content length
		length=file.length();
		res.setContentLengthLong(length);
		//get InputStream pointing to the file
		is=new FileInputStream(FILE_NAME);
		//get OutputStream pointing to response object
		sos=res.getOutputStream();
		//gives instruction to browser to make the output as downloadable file
		res.addHeader("Content-Disposition","attachment;fileName=james.png");
		//write buffer based logic for copying the content
		buffer=new byte[4096];
		while((bytesRead=is.read(buffer))!=-1){
			sos.write(buffer, 0, bytesRead);
		}//while
		//close steams
		is.close();
		sos.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
