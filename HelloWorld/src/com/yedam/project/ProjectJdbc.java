package com.yedam.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bookApp.Book;

public class ProjectJdbc {
	// Connection 생성.
		Connection getConnect() {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String userId = "scott";
			String userPw = "tiger";
			
			try {
				Connection conn = DriverManager.getConnection(url, userId, userPw);
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//추가.
		public boolean insert(Book book) {
			Connection conn = getConnect();
//			String sql = ;
			try {
//				Statement stmt = conn.createStatement();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, book.getTitle());
				stmt.setString(2, book.getAuthor());
				stmt.setString(3, book.getCompany());
				stmt.setInt(4, book.getPrice());
				int r = stmt.executeUpdate();
				if (r > 0) {
					return true; // 등록성공.
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false; // 등록실패.
		}
		//수정.
		public boolean update(Project project) {
			Connection conn = getConnect();
			String sql = "update tbl_book "
					+" set    book_title = nvl(?,book_title) " //
				    +"  ,price      = ? " //
				    +"  ,author     = nvl(?, author) " //
				    +"  ,company    = nvl(?, company) " //
				    +"where book_code   = ? " ;
			try {
//				Statement stmt = conn.createStatement();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, project.getName());
				stmt.setInt(2, project.getPrice());
				int r = stmt.executeUpdate();
				if (r > 0) {
					return true; // 등록성공.
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false; // 등록실패.
		}
		//삭제.
		public boolean delete(String coffeecode) {
			Connection conn = getConnect();
			String sql = "delete from tbl_book where book_code = " + coffeecode;

			try {
				Statement stmt = conn.createStatement();
				int r = stmt.executeUpdate(sql);
				if (r > 0) {
					return true; // 등록성공.
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false; // 등록실패.
		}
		
		//목록.10
		public List<Book> list(String company){
			List<Book> list = new ArrayList<Book>();
			Connection conn = getConnect();
			String sql = "Select  * from tbl_book " // 
					+ "where company = nvl(?, company) "  //
					+ "order by book_code";
			
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, company);
				
				ResultSet rs = psmt.executeQuery(); //조회
				while (rs.next()) {
					Book book = new Book();
					book.setName(rs.getString("namer"));
					book.setPrice(rs.getInt("price"));
					list.add(book);	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		} // end of 목록.
		public Book select(String bcode){
			Connection conn = getConnect();
			String sql = "Select  * from tbl_book " // 
					+ "where book_code = ?";
			
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, bcode);
				
				ResultSet rs = psmt.executeQuery(); //조회
				if (rs.next()) {
					Book book = new Book();
					book.setAuthor(rs.getString("author"));
					book.setBookCode(rs.getString("book_code"));
					book.setCompany(rs.getString("company"));
					book.setPrice(rs.getInt("price"));
					book.setTitle(rs.getString("book_title"));
					return book;	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null; // 조회결과 없음.
		}
	} // end of 클래스.

}
