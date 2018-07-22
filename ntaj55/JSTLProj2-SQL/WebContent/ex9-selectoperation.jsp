<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!-- setup DataSource object -->
<sql:setDataSource  var="ds" driver="oracle.jdbc.driver.OracleDriver"
                                          url="jdbc:oracle:thin:@localhost:1521:xe" user="system"
                                          password="manager" />
<!-- Create ResultSet obj by executing select Query -->
<sql:query var="rs" dataSource="${ds}" sql="select * from emp"/>
<!-- Process and Display ResultSet object -->
<c:forEach var="row" items="${rs.rows}">
    ${row.empno } &nbsp;&nbsp;&nbsp; ${row.ename} <br>
</c:forEach>
                                          
