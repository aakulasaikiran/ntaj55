package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.EmployeeBO;

public class EmployeeDAO {
	private static final String INSERT_QUERY="INSERT INTO EMPLOYEE_INFO VALUES(?,?,?,?,?,?,?)";
	private Connection getConnection()throws Exception{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		//get jdbc con obj from jdbc con pool
		ic=new InitialContext();
		ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");
		con=ds.getConnection();
		return con;
	}
	
	public int insert(EmployeeBO  bo)throws Exception{
		PreparedStatement ps=null;
		Connection con=null;
		int count=0;
		//write jdbc persistence logic to insert record
	   con=getConnection();
	   
	   ps=con.prepareStatement(INSERT_QUERY);
	   ps.setString(1,bo.getName());
	   ps.setString(2,bo.getAddrs());
	   ps.setInt(3,bo.getAge());
	   ps.setInt(4,bo.getExp());
	   ps.setString(5, bo.getSkill());
	   ps.setInt(6,bo.getSalary());
	   ps.setString(7,bo.getCity());
	   
	   count=ps.executeUpdate();
	   return count;
	}//insert(-)

}//class

/*SQL> create table Employee_info(name varchar2(20),
            addrs varchar2(20),age number(5),exp number(5),
            skill varchar2(20),salary number(9),city varchar2(15));
 */
