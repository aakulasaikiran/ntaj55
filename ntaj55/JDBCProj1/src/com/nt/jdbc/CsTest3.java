package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure AUTH_PRO(user in varchar,
        pass in varchar,
         result out varchar)as
cnt number(5);
begin
select count(*) into cnt from userlist where uname=user and pwd=pass;
if(cnt<>0) then
result:='Valid Credentials';
else
result:='InValid Credentials';
end if;
end;
/
*/
public class CsTest3 {
 private  static final String QUERY_AUTH="{ call AUTH_PRO(?,?,?)}" ;
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		CallableStatement cs=null;
		String result=null;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter username::");
				user=sc.nextLine();
				System.out.println("Enter password::");
				pass=sc.nextLine();
			}//if
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System", "manager");
			//create CallableStatement obj
			if(con!=null)
			  cs=con.prepareCall(QUERY_AUTH);
			//register OUT params with JDBC types
			if(cs!=null)
				cs.registerOutParameter(3,Types.VARCHAR);
			//set values to IN params
			if(cs!=null){
				cs.setString(1,user);
			    cs.setString(2, pass);
			}//if
			//call pl/sql procedure
			if(cs!=null)
				cs.execute();
			//gather results from OUT params
			if(cs!=null)
				result=cs.getString(3);
			
			System.out.println("Result:::"+result);
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
