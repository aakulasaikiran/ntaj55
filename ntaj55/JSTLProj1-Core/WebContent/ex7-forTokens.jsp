<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!-- Defined msg -->
  <c:set var="msg"  value="welcome to Naresh It" scope="request"/>
  <!-- Use Msg delimeter to split the String tokens -->
  <c:forTokens var="token" items="${msg}" delims=" ">
     ${token} <br>
  </c:forTokens>
  
  