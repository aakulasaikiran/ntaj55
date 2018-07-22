
<%
//read form data
  String iname=request.getParameter("iname");
  float price=Float.parseFloat(request.getParameter("iprice"));
  float qty=Float.parseFloat(request.getParameter("iqty"));
  //calc bill Amount
  float billAmt=price*qty;
   if(billAmt>=50000){ %>
      <jsp:forward page="discount.jsp">
         <jsp:param name="bAmt"  value="<%=billAmt%>"/>
      </jsp:forward>
   <% } 
     else{ %>
       Item name: <%=iname %> <br>
       Item price: <%=price %> <br>
       Item Quantity: <%=qty %> <br>
       Item Bill Amount: <%=billAmt %>
 <%  } %>

<br>
       <a href='form.html'>home</a>

