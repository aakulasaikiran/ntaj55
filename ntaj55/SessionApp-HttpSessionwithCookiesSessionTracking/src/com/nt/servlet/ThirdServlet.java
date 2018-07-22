package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeService;

public class ThirdServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   String name=null,addrs=null,skill=null,city=null;
	   int age=0,exp=0,salary=0;
	   EmployeeDTO dto=null;
	   HttpSession ses=null;
	   EmployeeService service=null;
	   String result=null;
	   RequestDispatcher rd=null;
        PrintWriter pw=res.getWriter();
	   //general settings
		 res.setContentType("text/html");
		 //read form3/req3 data
		 city=req.getParameter("city");
		 salary=Integer.parseInt(req.getParameter("salary"));
		 //access HttpSession object
		 ses=req.getSession(false);
		 //read form1/req1 and form2/req2 data from Session
		 name=(String)ses.getAttribute("name");
		 addrs=(String)ses.getAttribute("addrs");
		 age=(Integer)ses.getAttribute("age");
		 exp=(Integer)ses.getAttribute("exp");
		 skill=(String)ses.getAttribute("skill");
		 //prepare DTO having all 3 forms/requests data
		 dto=new EmployeeDTO();
		 dto.setName(name);
		 dto.setAddrs(addrs);
		 dto.setAge(age);
		 dto.setSkill(skill);
		 dto.setExp(exp);
		 dto.setSalary(salary);
		 dto.setCity(city);
		 //use Service class
		 service=new EmployeeService();
		 try{
		 result=service.registerEmployee(dto);
		 }
		 catch(Exception e){
			 rd=req.getRequestDispatcher("/err.html");
			 rd.forward(req,res);
		 }
		 //invalidate the Session
		 ses.invalidate();
		 //Display result
		 pw.println("<h1 style='color:red'>"+result+"</h1>");
		 //Display all the 3 forms data
		 pw.println("<br> form1/req1 data:::"+name+"...."+addrs+"...."+age);
		 pw.println("<br> form2/req2 data:::"+skill+"...."+exp);
		 pw.println("<br> form3/req3 data:::"+salary+"...."+city);
		 //add hyperlink
		 pw.println("<a href='personal.html'>home</a>");
		 pw.println("<br> Session Id:: "+ses.getId());
		 
		 //close stream
		 pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}
}//class
