<%@page import="java.io.*,java.sql.*" %>

<!-- get the path of the file to downloaded -->
<% 
String fileName="";
String queryText="";

//get connection with Db s/w
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
//Decide SQL Query
if(request.getParameter("resumeId")!=null)
     queryText="select emp_resume from employeereg where emp_id="+request.getParameter("resumeId");
else 
     queryText="select emp_pic from employeereg where emp_id="+request.getParameter("photoId");

//get File locaation from Db s/w
PreparedStatement ps=con.prepareStatement(queryText);
ResultSet rs=ps.executeQuery();
if(rs.next()){
  	  fileName=rs.getString(1);
}
    //get file length and set it as response content length
	  File file= new File(fileName);
      long length   =file.length();
      response.setContentLengthLong(length);
     //get file MIME type and make response content type
      String mimetype = application.getMimeType(fileName);
      response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
      
    //give instruction to browser to make the response (file content) as downloadable  file
      response.addHeader( "Content-Disposition", "attachment;filename="+fileName);
	  //get OuputStream pointing to response object
     OutputStream os = response.getOutputStream();
       //getInput Stream pointing to file
     InputStream is = new FileInputStream(file);

      // write buffer based logic to wite file content  to response object
      byte[] buffer = new byte[1024];
     int bytesRead=0;
      while (((bytesRead=is.read(buffer))!=-1))
      {
          os.write(buffer,0,bytesRead);
      }
    //close streams
      is.close();
      os.flush();
      os.close();
 %>