<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!-- set Locale -->
<fmt:setLocale value="de_DE" />
<!-- set Bundle -->
<fmt:setBundle basename="com.nt.commons.myfile"/>
<!-- format the msg collected from properties file -->
<fmt:message var="fmsg" key="wishMsg"/>
Wish Message ::: ${fmsg}. <br>

<!-- format the number-->
<fmt:formatNumber var="fnumber"  value="4567"/>
formatted number is :: ${fnumber} <br>

<!-- format the date-->
<jsp:useBean id="dt"  class="java.util.Date"/>
<fmt:formatDate var="fdate"  value="${dt}"/>
formatted dater is :: ${fdate}.







