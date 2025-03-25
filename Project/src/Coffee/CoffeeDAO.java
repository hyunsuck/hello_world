package Coffee;

import java.sql.*;
import java.util.*;

public class CoffeeDAO {
    private Connection conn;

    public CoffeeDAO() {
        try {
            // Oracle JDBC 연결 설정
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", // DB 주소
                "scott", // 사용자 ID
                "tiger"  // 비밀번호
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리 목록 조회
    public List<String> getCategories() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM Coffee";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return list;
    }

    // 카테고리에 따른 메뉴 조회
    public List<Coffee> getMenuByCategory(String category) {
        List<Coffee> list = new ArrayList<>();
        String sql = "SELECT * FROM Coffee WHERE category = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Coffee coffee = new Coffee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("category")
                );
                list.add(coffee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
