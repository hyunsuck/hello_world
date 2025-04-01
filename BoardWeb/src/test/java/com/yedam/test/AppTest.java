package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
//		BoardVO board = new BoardVO();
//		board.setTitle("매퍼테스트44");
//		board.setContent("매퍼를 활용한 입력테스트");
//		board.setWriter("newbie");
//		board.setBoardNo(5);
		
		try(SqlSession sqlSession =sqlSessionFactory.openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			
			 SearchDTO search = new SearchDTO();
			 search.setSearchCondition("T"); // 제목
	         search.setKeyword("연습");       // 검색어
	         search.setPage(1);              // 1페이지
//			
//			int r = mapper.insertBoard(board);//sqlSession.delete("com.yedam.mapper.BoardMapper.deleteBoard", board.getBoardNo());
//			if(r == 1) {
//				System.out.println("등록성공.");
//				sqlSession.commit(); //커밋
//			}else {
//				System.out.println("등록실패.");
//			}
			List<BoardVO> list = mapper.selectBoard(search);//.selectList("com.yedam.mapper.BoardMapper.selectBoard");
	        int total = mapper.selectTotal(search);
			for (BoardVO brd : list) {
				System.out.println(brd.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
