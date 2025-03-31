<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.mapper.BoardMapper"%>
<%@page import="com.yedam.common.DataSource"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>게시판</title>
</head>
<body>

<%
  // 글번호 파라미터 받기
  String bno = request.getParameter("bno");

  SqlSession sqlSession = DataSource.getInstance().openSession();
  BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

  if (bno == null) {
    // 목록 출력
    List<BoardVO> list = mapper.selectBoard();
%>

<h2>게시글 목록</h2>
<table border="1">
  <tr><th>번호</th><th>제목</th><th>작성자</th></tr>
  <% for (BoardVO board : list) { %>
  <tr>
    <td><%= board.getBoardNo() %></td>
    <td><a href="board.jsp?bno=<%= board.getBoardNo() %>"><%= board.getTitle() %></a></td>
    <td><%= board.getWriter() %></td>
  </tr>
  <% } %>
</table>

<%
  } else {
    // 상세 보기
    int boardNo = Integer.parseInt(bno);
    BoardVO board = mapper.selectOne(boardNo); // ← selectOne → selectBoard(int)
%>

<h2>게시글 상세</h2>
<p><strong>번호:</strong> <%= board.getBoardNo() %></p>
<p><strong>제목:</strong> <%= board.getTitle() %></p>
<p><strong>내용:</strong> <%= board.getContent() %></p>
<p><strong>작성자:</strong> <%= board.getWriter() %></p>
<p><strong>작성일:</strong> <%= board.getWriteDate() %></p>

<p><a href="board.jsp">← 목록으로 돌아가기</a></p>

<%
  }
%>

</body>
</html>
