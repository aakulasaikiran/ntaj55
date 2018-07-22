
<table border="0" width="100%" height="100%" rows="3" cols="3">
  <tr height="20%">
    <td colspan="3"><jsp:include page="/headurl" /> </td>
  </tr>
  <tr height="60%">
    <td width="20%"><jsp:include page="menu.jsp"/></td>
    <td width="50%"><jsp:include page="politics.jsp"/></td>
    <td width="30%">
      <table rows="2" cols="1">
        <tr>
          <td><jsp:include page="weather_report.jsp"/> </td>
          </tr>
          <tr>
          <td><jsp:include page="horoscope.jsp"/> </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr height="20%">
     <td colspan="3"><%@include file="footer.html" %></td>
  </tr>
</table>