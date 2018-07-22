<!-- <%!public String generateWishMsg(String uname) {
		java.util.Calendar calendar = null;
		int hour = 0;
// 		  int a=10+20;
		//get Sys Date
		calendar = java.util.Calendar.getInstance();
		hour = calendar.get(java.util.Calendar.HOUR); //24 hrs format
		if (hour <= 12)
			return "GM" + uname;
		else if (hour <= 16)
			return "GA" + uname;
		else if (hour <= 20)
			return "GE" + uname;
 		else
 			return "GN" + uname;
	}%> -->
	
<h1 style="color: red; text-align: center">Welcome to JSP</h1>
<br>
<!--  <b>Date and time :</b> -->
<%=new java.util.Date()%>
<%
	String user = "raja";
%>
Wish Msg ::
  <%-- <%=generateWishMsg(user)%> --%>
  
  <br> out obj class name  <%=out.getClass() %>
  <br> request obj class name <%=request.getClass() %>


