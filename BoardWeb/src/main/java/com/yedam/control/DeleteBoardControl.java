package com.yedam.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;

public class DeleteBoardControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int boardNo = Integer.parseInt(req.getParameter("bno"));

        SqlSession sqlSession = DataSource.getInstance().openSession(true);
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        int result = mapper.deleteBoard(boardNo);

        if (result > 0) {
            resp.sendRedirect("boardList.do");
        } else {
            System.out.println("삭제 실패: 게시글 번호 " + boardNo);
            req.setAttribute("error", "삭제에 실패했습니다.");
            req.getRequestDispatcher("/WEB-INF/views/deleteBoard.jsp").forward(req, resp);
        }
    }
}
