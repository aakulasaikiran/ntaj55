package com.nt.service;

import com.nt.bo.StudentBO;
import com.nt.dao.StudentDAO;
import com.nt.dto.StudentDTO;

public class StudentService {
	
	public String generateResult(StudentDTO dto){
		int total=0;
		float avg=0.0f;
		String result=null;
		StudentBO bo=null;
		StudentDAO dao=null;
		int count=0;
		 //write b.logic to calculate total,avg,result
		total=dto.getM1()+dto.getM2()+dto.getM3();
		avg=total/3.0f;
		 if(avg<35)
			 result="fail";
		 else
			 result="pass";
		 //prepre BO having persistable Data(sno,sname,total,avg,result)
		 bo=new StudentBO();
		 bo.setSno(dto.getSno());
		 bo.setSname(dto.getSname());
		 bo.setTotal(total);
		 bo.setAvg(avg);
		 bo.setResult(result);
		 //prepare DAO class
		 dao=new StudentDAO();
		 count=dao.insert(bo);
		 if(count==0)
			 return "Registration failed::: Result is"+result;
		 else
			 return "Registration succeded::: Result is"+result;
			 
	}

}
