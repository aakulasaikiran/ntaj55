package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.StudentDTO;
import com.nt.service.StudentService;
import com.nt.vo.StudentVO;

public class StudentControllerServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String sno=null,sname=null,m1=null,m2=null,m3=null;
		StudentVO vo=null;
		StudentDTO dto=null;
		StudentService service=null;
		String result=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		sno=req.getParameter("sno");
		sname=req.getParameter("sname");
		m1=req.getParameter("m1");
		m2=req.getParameter("m2");
		m3=req.getParameter("m3");
		//create VO class obj having form data
		vo=new StudentVO();
		vo.setSno(sno);
		vo.setSname(sname);
		vo.setM1(m1);
		vo.setM2(m2);
		vo.setM3(m3);
		//Convert VO class obj to DTO class obj
		dto=new StudentDTO();
		dto.setSno(Integer.parseInt(vo.getSno()));
		dto.setSname(vo.getSname());
		dto.setM1(Math.round(Float.parseFloat(vo.getM1())));
		dto.setM2(Math.round(Float.parseFloat(vo.getM2())));
		dto.setM3(Math.round(Float.parseFloat(vo.getM3())));
		//use Service class
		service=new StudentService();
		result=service.generateResult(dto);
		//display reusuls
		pw.println("<h1 style='text-align:center'>"+result+"</h1>");
		pw.println("<a href='form.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}//doPost(-,-)
}//class
