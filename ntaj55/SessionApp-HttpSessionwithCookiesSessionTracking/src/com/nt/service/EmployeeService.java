package com.nt.service;

import com.nt.bo.EmployeeBO;
import com.nt.dao.EmployeeDAO;
import com.nt.dto.EmployeeDTO;

public class EmployeeService {
	
	public String registerEmployee(EmployeeDTO dto)throws Exception{
		EmployeeBO bo=null;
		EmployeeDAO dao=null;
		int count=0;
		//convert EmployeeDTO class obj to EmployeeBO class obj
		bo=new EmployeeBO();
		bo.setName(dto.getName());
		bo.setAddrs(dto.getAddrs());
		bo.setAge(dto.getAge());
		bo.setExp(dto.getExp());
		bo.setSkill(dto.getSkill());
		bo.setSalary(dto.getSalary());
		bo.setCity(dto.getCity());
		//use DAO class
		dao=new EmployeeDAO();
		count=dao.insert(bo);
		//generate result
		if(count==0)
			return "Registration failed";
		else
			return "Registration Successfull";
	}//registerEmployee(-,-)

}//class
