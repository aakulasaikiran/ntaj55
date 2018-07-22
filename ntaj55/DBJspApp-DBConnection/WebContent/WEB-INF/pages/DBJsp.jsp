<%@page import="java.sql.*" %>
<%!
  Connection con;
  PreparedStatement ps1=null,ps2=null;
  ResultSet rs=null;
  public void jspInit(){
     ServletConfig cg=null;
     String driver=null,url=null,dbuser=null,dbpwd=null;
    //access ServletConfig obj
    cg=getServletConfig();
    //read jdbc properties ad init param values
     try{
      driver=cg.getInitParameter("driver");
      url=cg.getInitParameter("url");
      dbuser=cg.getInitParameter("dbuser");
      dbpwd=cg.getInitParameter("dbpwd");
      //load driver class and register it
      Class.forName(driver);
      //Establish the connection
      con=DriverManager.getConnection(url,dbuser,dbpwd);
      //create PreparedStatement objs
      ps1=con.prepareStatement("insert into student values(?,?,?)");
      ps2=con.prepareStatement("select * from student");
       }//try
       catch(SQLException se){
       se.printStackTrace();
       }
       catch(Exception e){
        e.printStackTrace();
         }
  }//jspInit()
 %>
 
  <%
    String pVal=null,sname=null,sadd=null;
    int sno=0,result=0;
    
    //read s1 request param value
    pVal=request.getParameter("s1");
    //write request processing logic
    if(pVal.equalsIgnoreCase("register")){  //for submit button
      //read form data
      sno=Integer.parseInt(request.getParameter("tno"));
      sname=request.getParameter("tname");
      sadd=request.getParameter("taddrs");
      //set values to positional params 
      ps1.setInt(1,sno);
      ps1.setString(2,sname);
      ps1.setString(3,sadd);
      //execute query
       result=ps1.executeUpdate();
       if(result==0){
        %>
        <h1> Registration failed</h1>
        <%}
         else{ %>
         <h1> Registration Succedded</h1>
        <% }
        }//if
        else{  //when hyperlink is clicked
         //execute Select Query
         rs=ps2.executeQuery();
         //process the Result
         %>
         <table border="1" align="center">
          <tr> <th>sno</th><th>sname</th><th>sadd</th></tr>
          <%
           while(rs.next()){ %>
            <tr>
              <td><%=rs.getInt(1)%></td>
              <td><%=rs.getString(2)%></td>
              <td><%=rs.getString(3)%></td>
            </tr>
             <% }  %>
         </table>
        
       <% }    %>
        <a href="input.html">home</a>
  
    <%!
      public void jspDestroy(){
        try{
           if(rs!=null)
             rs.close();
            }
            catch(SQLException se){
            se.printStackTrace();
           }
        try{
          if(ps1!=null)
             ps1.close();
            }
            catch(SQLException se){
            se.printStackTrace();
           }
           try{
           if(ps2!=null)
             ps2.close();
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
        
       }//jspDestroy()
     %>


