<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!-- loads file -->
<c:import var="file" url="orders.xml"/>
<!-- parses the file -->
<x:parse xml="${file}" var="doc"/>
<br>

printing all item deails <br>
<x:forEach  var="ord"  select="$doc/orders/order">
    Name: <x:out select="$ord/item"/><br>
    price: <x:out select="$ord/price"/><br>
</x:forEach>
<br>

printing items whose price >= 500 <br>
<x:forEach  var="ord"  select="$doc/orders/order">
  <x:if select="$ord/price>=500">
    Name: <x:out select="$ord/item"/><br>
    price: <x:out select="$ord/price"/><br>
  </x:if>
</x:forEach>
