<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Create variable having scope -->
<c:set var="attr1"   scope="request" value="val1"/>
<!-- Display variable from scope -->
<br>attr1 value: <c:out value="${attr1}"  />
<!-- Remove variable from scope -->
<c:remove var="attr1"  scope="request"/>
<!-- Display variable from scope -->
<br>attr1 value: <c:out value="${attr1}" />

