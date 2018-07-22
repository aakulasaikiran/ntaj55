package com.nt.jdbc;
/*create or replace procedure GET_EMP_DETAILS(no in number,
        name out varchar,
        salary out number,
        desg out varchar)as
begin
select  ename,sal,job  into name,salary,desg from emp where empno=no;
end; */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest2 {
  private static final String  CALL_PROCEDURE="{call GET_EMP_DETAILS(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("enter Employee no:: ");
				no=sc.nextInt();
			}//if
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System", "manager");
			//create CallableStatement obj
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			//register OUT params with JDBC Types
			if(cs!=null){
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.INTEGER);
				cs.registerOutParameter(4,Types.VARCHAR);
			}//if
			//set vlaues to IN params
			if(cs!=null){
				cs.setInt(1,no);
			}
			//call PL/sql Procedure
			if(cs!=null){
				cs.execute();
			}
			//gather results from OUT params
			if(cs!=null){
				System.out.println("Emp Name:"+cs.getString(2));
				System.out.println("Emp Salary:"+cs.getInt(3));
				System.out.println("Emp Desg :"+cs.getString(4));
			}//if
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
			//close jdbc objs
			try{
			 if(cs!=null)
				 cs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				 if(con!=null)
					 con.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try{
				 if(sc!=null)
					 sc.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
