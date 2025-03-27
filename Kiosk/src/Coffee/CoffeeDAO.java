package Coffee; // 패키지 선언

// JDBC 관련 클래스 임포트
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// 유틸리티 클래스 (List 등) 임포트
import java.util.ArrayList;
import java.util.List;

// 커피 데이터베이스 접근 객체 클래스
public class CoffeeDAO {
    private Connection conn; // DB 연결 객체

    // 생성자: 오라클 DB 연결 초기화
    public CoffeeDAO() {
        try {
            // Oracle JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // DB에 연결 (주소, ID, 비밀번호 지정)
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@192.168.0.39:1521:xe", // DB 주소
                "scott", // 사용자 ID
                "tiger"  // 비밀번호
            );
        } catch (Exception e) {
            e.printStackTrace(); // 예외 출력
        }
    }

    // 카테고리 목록 조회 메서드
    public List<String> getCategories() {
        List<String> list = new ArrayList<>(); // 결과 저장용 리스트
        String sql = "SELECT DISTINCT category FROM Coffee"; // 중복 없이 카테고리만 추출
        try (
            Statement stmt = conn.createStatement(); // SQL 실행 준비
            ResultSet rs = stmt.executeQuery(sql) // SQL 실행
        ) {
            while (rs.next()) { // 결과가 있으면 반복
                list.add(rs.getString("category")); // 카테고리 컬럼 값 추가
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 출력
        }
        return list; // 카테고리 리스트 반환
    }

    // 선택된 카테고리에 속한 메뉴 목록 조회 메서드
    public List<Coffee> getMenuByCategory(String category) {
        List<Coffee> list = new ArrayList<>(); // 결과 저장용 리스트
        String sql = "SELECT * FROM Coffee WHERE category = ?"; // 조건부 조회 쿼리
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement 사용
            pstmt.setString(1, category); // ?에 category 값 세팅
            ResultSet rs = pstmt.executeQuery(); // SQL 실행
            while (rs.next()) { // 결과가 있으면 반복
                // 한 행의 데이터를 Coffee 객체로 생성
                Coffee coffee = new Coffee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("category")
                );
                list.add(coffee); // 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 출력
        }
        return list; // 결과 리스트 반환
    }

    // DB 연결 종료 메서드
    public void close() {
        try {
            // 연결이 열려있으면 닫기
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 출력
        }
    }
}