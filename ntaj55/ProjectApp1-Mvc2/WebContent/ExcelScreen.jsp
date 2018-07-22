<%@page  import="java.util.*,com.nt.dto.BookDTO" %>


<h1><u> Books beloging to Category:::<%=request.getParameter("category") %></u></h1>


<%
  //gives instruction to browser to display output as downloadable file
  response.addHeader("Content-Disposition","attachment;fileName=booksList.xls");
  response.setContentType("application/vnd.ms-excel");
  
 //read request scope ListDTO
   List<BookDTO> listDTO=(List<BookDTO>)request.getAttribute("booksDTO");
   if(listDTO!=null){  %>
    <table  border="1">
       <tr bgcolor="red"><td>BookId</td><td>BookName</td><td>Author</td><td>Status</td><td>Category</td></tr>
       <%
         for(BookDTO dto:listDTO){ %>
           <tr bgcolor="cyan">
             <td><%=dto.getBookId() %></td>
             <td><%=dto.getBookName() %></td>
             <td><%=dto.getAuthor() %></td>
             <td><%=dto.getStatus() %></td>
             <td><%=dto.getCategory() %></td>
           </tr>
    <%   }//for
        %>
    </table>
    <br>
<%          
   }
   else{ %> 
      <h1> Books not Found</h1>
  <%  }
 %>



