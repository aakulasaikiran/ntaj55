<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*" %>
 <!--  Display 2nd table (traditional for loop)-->
 <c:forEach var="i" begin="1" end="10" step="1" >
    2 *  ${i } =  ${2*i } <br>
 </c:forEach>
 
 <!--  Enhanced for loop (array content)-->
 <%
   String names[]=new String[]{"raja","rani","ravi"};
   request.setAttribute("namesArray",names);
  %>
  <c:forEach var="name"  items="${namesArray}">
       ${name} <br>
  </c:forEach>
 <!--  Enhanced for loop (List content)-->
<%
   List<String> listColleges=new ArrayList();
   listColleges.add("CBIT"); listColleges.add("JBIT");
   request.setAttribute("listColleges",listColleges);
  %>
  <c:forEach var="college"  items="${listColleges}">
       ${college} <br>
  </c:forEach>
 
 