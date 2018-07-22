
<%//read form data
    String ss=null;
    ss=request.getParameter("ss");
    //perform sendRedirection to Google
     response.sendRedirect("https://www.google.co.in/search?q="+ss);
     %>