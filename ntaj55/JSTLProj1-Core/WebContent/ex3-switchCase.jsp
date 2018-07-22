<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<c:choose>
    <c:when test="${param.p<0 }">
        negetive number
    </c:when>
     <c:when test="${param.p>0 }">
        positive number
    </c:when>
    <c:otherwise>
        Zero..
    </c:otherwise>
</c:choose>
