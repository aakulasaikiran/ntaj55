
<% //create various scopes of attributes (generally created in controller servlet) 
       request.setAttribute("attr1","val1");
       session.setAttribute("attr2","val2");
       application.setAttribute("attr3","val3"); %>
       
   <!-- read and display attribute values -->
   attr1== ${requestScope.attr1} <br>
   attr2== ${sessionScope.attr2} <br>
   attr3== ${applicationScope.attr3} <br>
   
   attr3== ${attr3} <br>     <!-- internally uses pageContext.findAttribute(-) -->
   
   
       