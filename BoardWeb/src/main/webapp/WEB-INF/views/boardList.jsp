<%@page import="com.yedam.common.PageDTO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import ="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
 <!--  webapp/WEB-INF/views/boardList.jsp -->
 <%
  List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist");
  PageDTO pageDTO = (PageDTO) request.getAttribute("paging");
  String sc = (String) request.getAttribute("searchCondition");
  String kw = (String) request.getAttribute("keyword");
 %>
 <p><%=pageDTO %></p>
 <h3>게시글 목록(boardList.jsp)</h3>
 <!-- 검색조건. -->
 <form action="boardList.do" method="get">
  <div class="row mb-3">
    <div class="col-sm-4">
      <select name="searchCondition" class="form-control">
        <option value="">선택하세요</option>
         <option value="T" <%= "T".equals(sc) ? "selected" : "" %>>제목</option>
         <option value="W" <%= "W".equals(sc) ? "selected" : "" %>>작성자</option>
         <option value="TW" <%= "TW".equals(sc) ? "selected" : "" %>>제목&작성자</option>
      </select>
    </div>
    <div class="col-sm-4">
       <%if (kw != null && !kw.equals("null")) {%>
	      <input type="text" name="keyword" class="form-control" value="<%=kw%>">
	    <%} else {%>
	      <input type="text" name="keyword" class="form-control">
	    <%} %>
	</div>
    <div class="col-sm-2">
      <button type="submit" class="btn btn-info">검색</button>
    </div>
  </div>
</form>
 <table class="table">
  <thead>
  <tr><th>글번호</th><th>제 목</th><th>작성자</th><th>작성일시</th></tr>
  </thead>
  <tbody>
   <%for (BoardVO board : list ) { %>
     <tr>
        <td><%=board.getBoardNo() %></td>
        <td><a href='board.do?page=<%=pageDTO.getCurrentPage() %>&bno=<%=board.getBoardNo() %>'><%= board.getTitle() %></a></td>
        <td><%=board.getWriter() %></td>
        <td><%=board.getWriteDate() %></td>
    </tr>
    <%} %>
  </tbody>
 </table>
 <!-- paging 처리. -->
 <nav aria-label="Page navigation">
  <ul class="pagination">
 
   <!-- 이전 10개 페이지여부. -->
   <% if (pageDTO.isPrev()) { %>
      <li class="page-item">
        <a class="page-link" href="boardList.do?page=<%= pageDTO.getStartPage() - 1 %>&searchCondition=<%=sc%>&keyword=<%=kw%>">Previous</a>
      </li>
    <% } else { %>
      <li class="page-item disabled">
        <span class="page-link">Previous</span>
      </li>
    <% } %>
   
    <% for (int p = pageDTO.getStartPage(); p <= pageDTO.getEndPage(); p++) { %>
      <% if (p == pageDTO.getCurrentPage()) { %>
        <li class="page-item active" aria-current="page">
          <span class="page-link"><%= p %></span>
        </li>
      <% } else { %>
        <li class="page-item">
          <a class="page-link" href="boardList.do?page=<%= p %>&searchCondition=<%=sc%>&keyword=<%=kw%>"><%= p %></a>
        </li>
      <% } %>
    <% } %>
             
    <!-- 이후 10개 페이지여부. -->
    <% if (pageDTO.isNext()) { %>
      <li class="page-item">
        <a class="page-link" href="boardList.do?page=<%= pageDTO.getEndPage() + 1 %>&searchCondition=<%=sc%>&keyword=<%=kw%>">Next</a>
      </li>
    <% } else { %>
      <li class="page-item disabled">
        <span class="page-link">Next</span>
      </li>
    <% } %>
  </ul>
</nav>

<!-- <p><%= list %></p> -->
<jsp:include page="includes/footer.jsp"/>