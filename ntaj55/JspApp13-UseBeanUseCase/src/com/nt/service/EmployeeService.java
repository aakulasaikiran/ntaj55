package com.nt.service;

import com.nt.dto.EmployeeDTO;

public class EmployeeService {
	
	public void generateSalaries(EmployeeDTO dto){
		double gSalary=0.0,netSalary=0.0;
		//calculate gross,netSalaries
		gSalary=dto.getSalary()+dto.getSalary()*0.4;
		netSalary=gSalary-(gSalary*0.1);
		//set grossSalary,netSalary to dto
		dto.setGrossSalary(gSalary);
		dto.setNetSalary(netSalary);
	}//method
}//class
