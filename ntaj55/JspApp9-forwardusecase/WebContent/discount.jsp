
<%
  //read form data
  String iname=request.getParameter("iname");
  float price=Float.parseFloat(request.getParameter("iprice"));
  float qty=Float.parseFloat(request.getParameter("iqty"));
  //read additonal req param value
  float billAmt=Float.parseFloat(request.getParameter("bAmt"));
  //calc discount amount
  float discount=billAmt*0.3f;
  //calc final BillAmount
  float finalAmt=billAmt-discount;
  //dispplay details
%>
    Item name: <%=iname %> <br>
       Item price: <%=price %> <br>
       Item Quantity: <%=qty %> <br>
       Item Bill Amount: <%=billAmt %>
       Discount : <%=discount %> <br>
       Final Amount: <%=finalAmt %><br>
       
       <br>
       <a href='form.html'>home</a>
