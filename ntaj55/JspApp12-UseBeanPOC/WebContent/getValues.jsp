<%@page import="com.nt.beans.StudentBean"%>

<!-- create or locate bean class object -->
<jsp:useBean id="st"  class="com.nt.beans.StudentBean" scope="application"/>
<!--  display bean properties -->
sno=<jsp:getProperty name="st" property="sno"/><br>
sname=<jsp:getProperty name="st" property="sname"/><br>
result=<jsp:getProperty name="st" property="result"/><br>
