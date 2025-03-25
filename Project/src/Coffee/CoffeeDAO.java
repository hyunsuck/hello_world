package Coffee;

import java.sql.*;
import java.util.*;

public class CoffeeDAO {
    private Connection conn; // Oracle DB와의 연결 객체

    // 생성자: DB 연결 초기화
    public CoffeeDAO() {
        try {
            // 오라클 DB 접속 정보 (환경에 따라 수정 가능)
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; // xe → orcl 등으로 바뀔 수 있음
            String userId = "scott";     // 오라클 사용자 ID
            String userPw = "tiger"; // 오라클 비밀번호

            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DB 연결 시도
            conn = DriverManager.getConnection(url, userId, userPw);
        } catch (Exception e) {
            // 연결 실패 시 오류 출력
            e.printStackTrace();
        }
    }

    // 카테고리 목록 가져오기 (중복 없이 정렬된 순서로)
    public List<String> getCategories() {
        Set<String> categorySet = new LinkedHashSet<>(); // 중복 제거 & 입력 순서 유지
        String sql = "SELECT DISTINCT category FROM menu_items ORDER BY category";

        try (
            Statement stmt = conn.createStatement();       // SQL 실행 객체 생성
            ResultSet rs = stmt.executeQuery(sql)          // SQL 실행 및 결과 받기
        ) {
            while (rs.next()) {
                String category = rs.getString("category");      // 카테고리 컬럼 값 가져오기
                categorySet.add(category); // 카테고리 추가
            }
        } catch (SQLException e) {
            // SQL 오류 발생 시 출력
            e.printStackTrace();
        }

        // Set을 List로 변환하여 반환
        return new ArrayList<>(categorySet);
    }

    // 특정 카테고리의 메뉴 목록 가져오기
    public List<Coffee> getMenuByCategory(String category) {
        List<Coffee> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE category = ? ORDER BY id";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category); // ?에 카테고리 값 설정
            ResultSet rs = pstmt.executeQuery(); // 실행 결과 받아오기

            while (rs.next()) {
                // DB에서 가져온 레코드를 Coffee 객체로 변환
                Coffee c = new Coffee(
                    rs.getInt("id"),
                    rs.getString("category"),
                    rs.getString("name"),
                    rs.getInt("price")
                );
                menuList.add(c); // 메뉴 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList; // 최종 메뉴 리스트 반환
    }

    // DB 연결 종료
    public void close() {
        try {
            if (conn != null) conn.close(); // 연결 닫기
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
