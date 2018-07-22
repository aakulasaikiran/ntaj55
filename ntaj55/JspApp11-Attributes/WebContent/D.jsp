  <h1> from D.jsp</h1><br>
  
<!-- read and display attribute value -->
attr1 page scope value:: <%=pageContext.findAttribute("attr1") %> <br> 
attr2 request scope value:: <%=pageContext.findAttribute("attr2") %> <br>
attr3  session  scope value:: <%=pageContext.findAttribute("attr3") %> <br>
attr4  application  scope value:: <%=pageContext.findAttribute("attr4") %> <br>


 
  