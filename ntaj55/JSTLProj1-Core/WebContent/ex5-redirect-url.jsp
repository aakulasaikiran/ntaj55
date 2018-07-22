<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!-- prepare url -->
<c:url var="gurl" value="https://google.co.in/search"/>

<!-- Redirect to google.com -->
<c:redirect url="${gurl}">
  <c:param name="q" value="ameerpet"></c:param>
</c:redirect>

