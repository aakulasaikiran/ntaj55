package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
	   try{
		   //locate the file
		   is=new FileInputStream("src\\com\\nt\\commons\\myfile.properties");
		   //load the content of properties file to java.util.Properties class obj
		   props=new Properties();
		   props.load(is);
		   //display the data
		   System.out.println("content is--->"+props.toString());
		   System.out.println(" the key name value is"+props.getProperty("name"));
	   }//try
	   catch(IOException ioe){
		   ioe.printStackTrace();
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	}//main
}//class
