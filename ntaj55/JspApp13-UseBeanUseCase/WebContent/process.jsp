<%@page import="com.nt.service.*,com.nt.dto.*" %>

<!-- create or locate EmployeeDTO class obj -->
<jsp:useBean id="dto"  class="com.nt.dto.EmployeeDTO" scope="request"/>
<!-- set request param  Values(form data) to Bean properties -->
<jsp:setProperty name="dto"  property="*" />
<!-- use Service  class-->
<jsp:useBean id="service"  class="com.nt.service.EmployeeService" scope="request"/>
<%
   service.generateSalaries(dto);
 %>
 
<!--  read and display bean property values(inputs,outputs) -->
employee no::: <jsp:getProperty  name="dto" property="eno"/> <br>
employee name::: <jsp:getProperty name="dto" property="ename"/> <br>
employee salary::: <jsp:getProperty name="dto" property="salary"/> <br>
<br><br>
Employee Gross Salary:: <jsp:getProperty name="dto"  property="grossSalary"/><br>
Employee Net Salary:: <jsp:getProperty name="dto"  property="netSalary"/><br>

<br><br>
<a href="form.html">Get Another Employee Salary Details </a>





