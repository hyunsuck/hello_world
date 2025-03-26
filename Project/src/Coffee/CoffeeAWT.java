package Coffee; // 패키지 선언

import java.awt.*; // AWT UI 요소 임포트
import java.awt.event.*; // AWT 이벤트 처리용 클래스 임포트
import java.util.List; // List 인터페이스 사용을 위한 임포트

@SuppressWarnings("serial")
public class CoffeeAWT extends Frame { // Frame을 상속받은 메인 클래스
    private List<Coffee> currentMenu; // 현재 선택된 메뉴 목록
    private CoffeeDAO dao; // DB 접근 객체
    private List<String> categories; // 카테고리 목록
    private List<String> cartItems = new java.util.ArrayList<>(); // 장바구니 항목 저장 리스트
    private int totalPrice = 0; // 총 결제 금액
    private int waitNumber = 1; // 대기번호 (자동 증가)

    private TextArea cartArea; // 장바구니 내용 표시 영역
    private Panel menuPanel; // 메뉴 버튼들이 들어갈 패널
    private Panel topPanel; // 카테고리 버튼 영역
    private Panel orderTypePanel; // 주문 타입 선택 영역

    public CoffeeAWT() { // 생성자: UI 설정 및 초기화
        dao = new CoffeeDAO(); // DAO 객체 생성
        categories = dao.getCategories(); // 카테고리 목록 가져오기

        setTitle("AWT 커피 키오스크"); // 창 제목 설정
        setSize(800, 600); // 창 크기 설정
        setLayout(new BorderLayout()); // BorderLayout 사용

     // 주문 타입 선택 패널 생성 및 버튼 추가
        orderTypePanel = new Panel(new GridBagLayout()); // 중앙 정렬을 위한 레이아웃 설정
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);

        Button storeBtn = new Button("매장 주문"); // 매장 주문 버튼 생성
        storeBtn.setFont(new Font("SansSerif", Font.BOLD, 18)); // 버튼 폰트 설정
        storeBtn.setPreferredSize(new Dimension(150, 50)); // 버튼 크기 설정

        Button takeoutBtn = new Button("포장 주문"); // 포장 주문 버튼 생성
        takeoutBtn.setFont(new Font("SansSerif", Font.BOLD, 18)); // 버튼 폰트 설정
        takeoutBtn.setPreferredSize(new Dimension(150, 50)); // 버튼 크기 설정

        storeBtn.addActionListener(e -> showCategoryButtons()); // 매장 주문 클릭 시 카테고리 버튼 표시
        takeoutBtn.addActionListener(e -> showCategoryButtons()); // 포장 주문 클릭 시 카테고리 버튼 표시

        gbc.gridx = 0;
        orderTypePanel.add(storeBtn, gbc); // 매장 주문 버튼을 좌측에 배치

        gbc.gridx = 1;
        orderTypePanel.add(takeoutBtn, gbc); // 포장 주문 버튼을 우측에 배치

        add(orderTypePanel, BorderLayout.NORTH); // 프레임 상단에 추가

        topPanel = new Panel(new FlowLayout()); // 카테고리 버튼 영역 초기화

        menuPanel = new Panel(); // 메뉴 버튼 표시 영역 초기화
        menuPanel.setLayout(new GridLayout(0, 1)); // 메뉴 패널을 세로 방향으로 설정
        add(new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED).add(menuPanel), BorderLayout.CENTER); // 메뉴 패널을 스크롤 가능한 형태로 가운데에 배치

        Panel rightPanel = new Panel(new BorderLayout()); // 오른쪽 패널 생성
        cartArea = new TextArea(); // 장바구니 출력 영역 생성
        cartArea.setEditable(false); // 편집 불가 설정
        rightPanel.add(cartArea, BorderLayout.CENTER); // 장바구니 영역 중앙 배치

        Panel btnPanel = new Panel(new GridLayout(1, 2)); // 결제/초기화 버튼 영역

        Button payButton = new Button("결제"); // 결제 버튼 생성
        payButton.addActionListener(e -> handlePayment()); // 클릭 시 결제 로직 실행

        Button clearButton = new Button("초기화"); // 초기화 버튼 생성
        clearButton.addActionListener(e -> { // 클릭 시 장바구니 초기화
            cartItems.clear();
            totalPrice = 0;
            cartArea.setText("");
        });

        btnPanel.add(payButton); // 버튼 패널에 결제 버튼 추가
        btnPanel.add(clearButton); // 버튼 패널에 초기화 버튼 추가
        rightPanel.add(btnPanel, BorderLayout.SOUTH); // 버튼 패널을 오른쪽 하단에 추가

        add(rightPanel, BorderLayout.EAST); // 전체 오른쪽에 장바구니 영역 추가

        // 창 닫기 이벤트 설정
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // 창 닫을 때 메모리 해제 및 종료
            }
        });
    }

    private void showCategoryButtons() { // 주문 방식 선택 후 카테고리 버튼 출력 함수
        remove(orderTypePanel); // 주문 방식 패널 제거
        topPanel.removeAll(); // 기존 버튼 제거 (중복 방지)

        for (String cat : categories) { // 카테고리마다 버튼 생성
            Button btn = new Button(cat);
            btn.addActionListener(e -> showMenu(cat)); // 버튼 클릭 시 해당 카테고리 메뉴 출력
            topPanel.add(btn);
        }

        add(topPanel, BorderLayout.NORTH); // 카테고리 버튼 패널을 상단에 추가
        validate(); // 레이아웃 다시 계산
        repaint(); // 화면 갱신
    }

    private void showMenu(String category) { // 카테고리 클릭 시 메뉴 출력 함수
        currentMenu = dao.getMenuByCategory(category); // 해당 카테고리의 메뉴 조회
        menuPanel.removeAll(); // 이전 메뉴 제거

        for (Coffee coffee : currentMenu) { // 메뉴마다 버튼 생성
            Button btn = new Button(coffee.getName() + " - " + coffee.getPrice() + "원");
            btn.addActionListener(e -> { // 클릭 시 수량 입력 받고 장바구니에 추가
                String qtyStr = showInputDialog(coffee.getName() + " 수량 입력:");
                try {
                    int qty = Integer.parseInt(qtyStr); // 입력된 수량 파싱
                    int total = qty * coffee.getPrice(); // 항목 총 가격 계산
                    String item = coffee.getName() + " x" + qty + " - " + total + "원";
                    cartItems.add(item); // 장바구니에 항목 추가
                    totalPrice += total; // 총 가격 증가
                    updateCartArea(); // 장바구니 출력 갱신
                } catch (Exception ex) {
                    showMessage("올바른 숫자를 입력하세요."); // 예외 처리
                }
            });
            menuPanel.add(btn); // 메뉴 버튼 추가
        }

        menuPanel.revalidate(); // 레이아웃 재계산
        menuPanel.repaint(); // 화면 갱신
    }

    private void updateCartArea() { // 장바구니 내용 갱신 함수
        cartArea.setText(""); // 초기화
        for (String item : cartItems) { // 항목별 출력
            cartArea.append(item + "\n");
        }
        cartArea.append("\n총 금액: " + totalPrice + "원"); // 총 금액 표시
    }

    private void handlePayment() { // 결제 처리 함수
        if (cartItems.isEmpty()) { // 장바구니 비었을 경우 처리
            showMessage("장바구니가 비어 있습니다.");
            return;
        }
        String input = showInputDialog("총 금액은 " + totalPrice + "원입니다. 지불 금액 입력:"); // 결제 금액 입력 요청
        try {
            int paid = Integer.parseInt(input); // 입력 금액 파싱
            if (paid < totalPrice) {
                showMessage("금액이 부족합니다."); // 부족 시 알림
                return;
            }
            int change = paid - totalPrice; // 거스름돈 계산
            showMessage("결제 완료!\n대기번호: " + waitNumber++ + "\n거스름돈: " + change + "원"); // 결제 완료 메시지
            cartItems.clear(); // 장바구니 초기화
            totalPrice = 0;
            updateCartArea(); // 장바구니 출력 갱신
        } catch (Exception e) {
            showMessage("숫자를 입력하세요."); // 잘못된 입력 처리
        }
    }

    private String showInputDialog(String message) { // 입력 다이얼로그 함수
        return javax.swing.JOptionPane.showInputDialog(null, message);
    }

    private void showMessage(String message) { // 메시지 다이얼로그 함수
        javax.swing.JOptionPane.showMessageDialog(null, message);
    }

    public static void main(String[] args) { // 메인 함수
        CoffeeAWT app = new CoffeeAWT(); // 객체 생성
        app.setVisible(true); // 창 표시
    }
}
