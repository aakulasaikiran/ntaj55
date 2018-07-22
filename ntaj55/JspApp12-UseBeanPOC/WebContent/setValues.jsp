<%@page import="com.nt.beans.StudentBean"%>

<!-- create or locate bean class object -->
<jsp:useBean id="st"  class="com.nt.beans.StudentBean" scope="application"/>
<%-- <!--  set values to bean properties -->
<jsp:setProperty name="st" property="sno" value="1001"/>
<jsp:setProperty name="st" property="sname" value="raja"/>
<jsp:setProperty name="st" property="result" value="pass"/> --%>

 <%-- <!--  set values to bean properties -->
<jsp:setProperty name="st" property="sno"  param="stno"/>
<jsp:setProperty name="st" property="sname" param="stname"/>
<jsp:setProperty name="st" property="result" param="stresult"/>  --%>

<!--  set values to bean properties -->
<jsp:setProperty name="st" property="*"/>


<br><b><i> values are set</i></b>