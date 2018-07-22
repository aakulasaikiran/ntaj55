package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TxMgmtTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcNo=0,destNo=0;
		int amt=0;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
	try{
		//read inputs
		sc=new Scanner(System.in);
		if(sc!=null){
			System.out.println("Enter Source Account no:");
			srcNo=sc.nextInt();
			System.out.println("Enter Dest Account no::");
			destNo=sc.nextInt();
			System.out.println("Enter Amount to transfer");
			amt=sc.nextInt();
		}
		//register jdbc driver s/w
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager"); 
		   //begin Transaction (Tx)
		  if(con!=null)
			  con.setAutoCommit(false);
		  //create Statement obj
		  if(con!=null)
			  st=con.createStatement();
		  // execute logics (Transfermoeny logics)
		   
		  if(st!=null){
			  //for withdraw
		      st.addBatch("update jdbc_account set balance=balance-"+amt+" where acno="+srcNo);
		      //for deposite
		      st.addBatch("update jdbc_account set balance=balance+"+amt+" where acno="+destNo);
		  }//if
		  
		  //execute batch
		  if(st!=null){
			  result=st.executeBatch();
		  }
		  
		  //perform Transaction mgment
		  if(result!=null){
			   for(int i=0;i<result.length;++i){
				   if(result[i]==0){
					   flag=true;
					   break;
				   }//if
				   
			   }//for
		  }//if
		  
		  if(flag==true){
			  con.rollback();
			  System.out.println("Money not transfered(Tx rolledback)");
		  }
		  else{
			  con.commit();
			  System.out.println("Money  transfered(Tx committed)");
		  }
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
			if(st!=null)
				st.close();
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
