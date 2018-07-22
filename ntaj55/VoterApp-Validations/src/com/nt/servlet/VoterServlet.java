//VoterServlet.java
package com.nt.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class VoterServlet extends HttpServlet
{
 
  public void doGet(HttpServletRequest req,
	                                    HttpServletResponse res)throws ServletException,IOException
	{
	  System.out.println("VoterServlet:doGet(-,-) method");
	   PrintWriter pw=null;
        String name=null,tage=null;
		int age=0;
		List<String> errList=new ArrayList<String>();
		String vstatus=null;
	    //general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("pname");  //raja
		tage=req.getParameter("page");  //30
		// Read vflg value to know weather client side form validations are performed or not
         vstatus=req.getParameter("vflag");
		 if(vstatus.equalsIgnoreCase("no")){
        //perform server side form validations
		System.out.println("Server side form validations");
		 if(name==null || name.equals("")|| name.length()==0){ //required
			 errList.add("Person name is required");
		 }
		 if(tage==null || tage.equals("") || tage.length()==0){ //required rule
		     errList.add("PErson age is required");
		 }
		 else{    //checking wheather age is numeric value or not
             try{
             age=Integer.parseInt(tage); 
			 }
			 catch(NumberFormatException nfe){
               errList.add("Person age mut be numeric value");
			 }//catch
		 }//else
         if(errList.size()>0){
			 pw.println("<ul>");
           for(String errMsg:errList){
			   pw.println("<li style='color:red'>"+errMsg+"</li>");
		   }//for
		     pw.println("</ul>");
		  return;		  
		 }//if 
		}//if
		else{
			age=Integer.parseInt(tage);
		}
		
		 //request processing logic (B.logic)
		if(age>=18)
			pw.println("<h1 style='color:blue'>"+name +" u  r elgible to vote</h1>");
		else
			pw.println("<h1 style='color:yellow'>"+name +" u  r not elgible to vote</h1>");
		  //add graphical hyperlink
		  pw.println("<a href='input.html'><img src='james.png'></a>");
		  //close stream
		  pw.close();
	}//service(-,-)
  public void doPost(HttpServletRequest req,
	                                    HttpServletResponse res)throws ServletException,IOException
	{
	  System.out.println("VoterServlet:doPost(-,-)method");
	   doGet(req,res);
    }
}//class