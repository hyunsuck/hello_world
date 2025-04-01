<%@page import="com.yedam.vo.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<h3>삭제화면(deleteForm.jsp)</h3>
<%
  BoardVO board = (BoardVO) request.getAttribute("board");
%>
<form action="deleteBoard.do">
  <input type="hidden" name="bno" value="<%= board.getBoardNo() %>">
  <table class="table">
    <tr>
      <th>글번호</th><td><%= board.getBoardNo() %></td>
      <th>작성자</th><td><%= board.getWriter() %></td>
    </tr>
    <tr>
      <th>제목</th>
      <td colspan="3"><%= board.getTitle() %></td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan="3">
        <div class="border rounded p-2 bg-light"><%= board.getContent() %></div>
      </td>
    </tr>
    <tr>
      <th>작성일시</th>
      <td colspan="3"><%= board.getWriteDate() %></td>
    </tr>
    <tr>
      <td colspan="4" align="center">
        <button type="submit" class="btn btn-danger"
         onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
        <a href="boardList.do" class="btn btn-secondary">취소</a>
      </td>
    </tr>
  </table>
</form>

<jsp:include page="includes/footer.jsp" />
