package com.nt.jdbc;
/*create or replace procedure first_pro1(x in number,y out number)as
begin
  y:=x*x;
end;
/
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest1 {
 private static final String  CALL_FIRST_PRO1="{ call first_pro1(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		int result=0;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student no::");
				no=sc.nextInt();
			}//if
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System", "manager");
			//create CallableStaement obj
			if(con!=null)
				cs=con.prepareCall(CALL_FIRST_PRO1);
			//register OUT param with JDBC types
			if(cs!=null)
				cs.registerOutParameter(2,Types.INTEGER);
			//set values to IN params
			if(cs!=null)
				cs.setInt(1,no);
			//call pl/sql procedure
			 if(cs!=null)
				 cs.execute();
			 //gather results from OUT params
			 if(cs!=null)
				result=cs.getInt(2);
			 System.out.println("The SUARE VALUE of"+no+" is"+result);
			 System.out.println("cs obj class name"+cs.getClass());
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
