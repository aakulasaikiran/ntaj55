<%@page  import="java.util.*,com.nt.dto.BookDTO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script language="JavaScript">
   function showprint(){
       frames.focus();
       frames.print();
   }
</script>

<%-- <h1><u> Books beloging to Category:::<%=request.getParameter("category") %></u></h1>


<%
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
      <a href="javascript:showprint()">print</a>
    
<%          
   }
   else{ %> 
      <h1> Books not Found</h1>
  <%  }
 %>
 --%>
<h1><u> Books beloging to Category::: ${param.category }<br>
<c:choose>
   <c:when test="${!empty booksDTO}">
      <table>
        <tr><th>BookId</th><th>BookName</th><th>Author</th><th>Status</th><th>Category</th></tr>
         <c:forEach var="bookDTO" items="${booksDTO}">
          <tr>
             <td>${bookDTO.bookId} </td>
             <td>${bookDTO.bookName}</td>
             <td>${bookDTO.author}</td>
             <td>${bookDTO.status}</td>
             <td>${bookDTO.category}</td>
          </tr>
         </c:forEach>
      </table>
   </c:when>
   <c:otherwise>
       Books not found
   </c:otherwise>
 </c:choose>

