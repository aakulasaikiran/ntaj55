package com.nt.basics;

import java.text.SimpleDateFormat;

public class ConversionsTest {

	public static void main(String[] args) throws Exception{
		
		//converting String date value to java.util.Date class obj
		String s1="40-20-1985"; //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udob=sdf.parse(s1);
		System.out.println("util  date is"+udob);
		
		//Converting  java.util.Date class obj to java.sql.Date class obj
		long ms=udob.getTime();
		java.sql.Date sqdob=new java.sql.Date(ms);
		System.out.println("sql date is"+sqdob);
		
		/*if String date value pattern is "yyyy-MM-dd" then it can be
		 converted directly  to java.sql.Date class obj with out converting
		 to java.util.Date class obj*/
		String s2="2010-10-20";  //yyyy-MM-dd
        java.sql.Date sqdoj=java.sql.Date.valueOf(s2);		
        System.out.println("sql date "+sqdoj);
        
        /* Converting java.sql.Date class obj to java.util.Date class obj */
        java.util.Date udoj=(java.util.Date)sqdoj;
        System.out.println("util date"+udoj);
        /* Converting java.util.Date class obj to  String date value */
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MMM-dd");
        String  s3=sdf2.format(udoj);
        System.out.println(" String date :::"+s3);
        
	

	}

}
