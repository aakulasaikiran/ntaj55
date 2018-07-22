package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PMDTest {

    public static void main(java.lang.String[] args)
    {
        Connection con=null;	
        PreparedStatement ps=null;
        ParameterMetaData pmd=null;
        int count=0;
     try{
    	  
      /*  //register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");*/
    	 
    	 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		//establish the connection
 		 con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");
    	 
		 //create PreparedStatement obj
		 if(con!=null)
		     ps =	con.prepareStatement("insert into student values(?, ?, ?)");
        //create ParameterMetaData obj
		 if(ps!=null)
		    pmd=ps.getParameterMetaData();
			System.out.println("pmd obj class name is "+pmd.getClass());
         // display the details about parameters
			if(pmd!=null){
            count=pmd.getParameterCount();

            for (int i =1; i<=count; i++) {
                 System.out.println("Parameter number " + i);
          
                System.out.println("  Mode is " + pmd.getParameterMode(i));
                System.out.println("  Type is " + pmd.getParameterType(i));
                System.out.println("  Type name is " + pmd.getParameterTypeName(i));
                System.out.println("  Precision is " + pmd.getPrecision(i));
                System.out.println("  Scale is " + pmd.getScale(i));
                System.out.println("  Nullable? is " + pmd.isNullable(i));
                System.out.println("  Signed? is " + pmd.isSigned(i));
             }//for
			}//if
     }//try
 	catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
	} catch (SQLException se) {
		se.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// close jdbc objs
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if (con != null)
				con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		} // finally
    }//main
}//class