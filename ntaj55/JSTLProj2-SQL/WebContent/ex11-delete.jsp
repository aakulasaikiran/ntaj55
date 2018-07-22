<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!-- setup DataSource object -->
<sql:setDataSource  var="ds" driver="oracle.jdbc.driver.OracleDriver"
                                          url="jdbc:oracle:thin:@localhost:1521:xe" user="system"
                                          password="manager" />
<!-- delete query -->
<sql:transaction dataSource="${ds}">
   <sql:update var="count" sql="delete from emp where empno=?">
      <sql:param >7934</sql:param>
   </sql:update>
</sql:transaction>                                          
 ${count} no.of records are deleted.