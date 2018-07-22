<%@page import="com.nt.service.*"%>

<!--  load class -->
<jsp:useBean id="rotator" class="com.nt.service.RotatorService" scope="application"/>

<%  response.setIntHeader("refresh",2);
    rotator.nextAdvertisement();
 %>
 <!-- Dispaly advertisement as graphical hyperlink -->
 <a href="<jsp:getProperty name="rotator" property="url"/>">
   <img src="<jsp:getProperty name="rotator" property="image"/>"  width="400" height="300"/>
 </a>



