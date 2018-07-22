<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql"  prefix="sql" %>
<!-- establish the connection -->
<sql:setDataSource  var="ds" driver="oracle.jdbc.driver.OracleDriver"
                                          url="jdbc:oracle:thin:@localhost:1521:xe" user="system"
                                          password="manager" />
<!--  parepare update SQL Query -->
<sql:update dataSource="${ds}" var="count"  sql="Update emp set sal=sal+? where empno=?">
   <sql:param value="2000"/>
   <sql:param>7499</sql:param>
</sql:update>
<br>
${count} no.of records are updated.

                                          
                                          




