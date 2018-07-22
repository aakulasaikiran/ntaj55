<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="msg"  value="hello how are u ?"/>

length: ${fn:length(msg) }  <br>
uppercase: ${fn:toUpperCase(msg) } <br>
lowercase: ${fn:toLowerCase(msg) } <br>
index of 'are': ${fn:indexOf(msg,"are") } <br>
contains are? :  ${fn:contains(msg,"are")} <br>
stats with hello? ${fn:startsWith(msg,"hello") } <br>



