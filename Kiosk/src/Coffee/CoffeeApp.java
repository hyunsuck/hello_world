package Coffee; // 패키지 선언

// 유틸리티 클래스 임포트
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeApp { // 메인 콘솔 앱 클래스
    static int waitNumber = 1; // 대기번호 정적 변수 (프로그램 내에서 증가)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 사용자 입력용 스캐너
        CoffeeDAO dao = new CoffeeDAO(); // DB 연동 DAO 객체 생성
        List<Coffee> cart = new ArrayList<>(); // 장바구니 역할을 하는 리스트

        boolean run = true; // 프로그램 실행 여부
        
        while(run) {//
        //주문 방식 추가
        System.out.println("==== 주문 방법 선택 ====");
        System.out.println("1. 매장 주문");
        System.out.println("2. 포장 주문");
        System.out.println("9. 종료");
        System.out.println("====================");
        System.out.print("선택>> ");
        int orderType = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기	
        
        if (orderType == 9) {
            run = false;
            System.out.println("프로그램을 종료합니다.");
            break;
        } else if (orderType != 1 && orderType != 2) {
            System.out.println("올바른 주문 방식을 선택해주세요.");
            continue;
        }
        
        
        
        	boolean ordering = true;
        	while (ordering) {
        		List<String> categories = dao.getCategories(); // DB에서 카테고리 목록 조회
            
        		// 메인 메뉴 출력
        		System.out.println("===== [메뉴 선택] =====");
	            for (int i = 0; i < categories.size(); i++) {
	                System.out.printf("%d. %s\n", i + 1, categories.get(i));
	            }
	            int cartMenu = categories.size() + 1; // 장바구니 메뉴 번호
	            int payMenu = categories.size() + 2; // 결제 메뉴 번호
	            System.out.println(cartMenu + ". 장바구니 확인/수정");
	            System.out.println(payMenu + ". 결제");
	            System.out.println("9. 종료");
	            System.out.println("====================");
	            System.out.print("선택>> ");
	            int menu = 9; // 기본값은 종료
	            while (true) {
	                try {
	                    menu = Integer.parseInt(scanner.nextLine()); // 숫자 입력 처리
	                    break;
	                } catch (NumberFormatException e) {
	                    System.out.println("숫자만 입력하세요.");
	                }
	            }
	
	            switch (menu) {
	                case 1, 2, 3: // 카테고리 메뉴 처리
	                    if (menu <= categories.size()) {
	                        String selectedCategory = categories.get(menu - 1); // 선택한 카테고리
	                        List<Coffee> menuList = dao.getMenuByCategory(selectedCategory); // 해당 메뉴 목록 불러오기
	                        MenuSelection(scanner, cart, menuList, selectedCategory); // 메뉴 선택 함수 호출
	                    }
	                    break;
	                case 4: // 장바구니 확인/수정
	                    CartMenu(scanner, cart, dao);
	                    break;
	                case 5: // 결제 진행
	                    Payment(scanner, cart, dao);
	                    break;
	                case 9: // 종료
	                    run = false;
	                    System.out.println("프로그램을 종료합니다.");
	                    break;
	                default:
	                    System.out.println("올바른 메뉴를 선택하세요.");
	            }
	        }
        }

        dao.close(); // DB 연결 종료
        scanner.close(); // 스캐너 종료
    }

    // 메뉴 선택 및 장바구니 담기 처리 함수
    public static void MenuSelection(Scanner scanner, List<Coffee> cart, List<Coffee> menu, String category) {
        System.out.println("[" + category + " 메뉴]");
        for (int i = 0; i < menu.size(); i++) {
            Coffee item = menu.get(i);
            System.out.printf("%d. %s - %d원\n", i + 1, item.getName(), item.getPrice()); // 메뉴 출력
        }

        while (true) {
            System.out.print("메뉴 번호 선택: ");
            int menuChoice = scanner.nextInt(); // 메뉴 번호 입력

            if (menuChoice >= 1 && menuChoice <= menu.size()) {
                Coffee selected = menu.get(menuChoice - 1); // 선택된 메뉴
                System.out.print("수량 입력: ");
                int qty = scanner.nextInt(); // 수량 입력

                boolean alreadyInCart = false; // 이미 장바구니에 있는지 확인
                for (Coffee item : cart) {
                    if (item.getId() == selected.getId()) {
                        item.addQuantity(qty); // 수량 추가
                        alreadyInCart = true;
                        break;
                    }
                }

                if (!alreadyInCart) {
                    selected.addQuantity(qty); // 수량 설정
                    cart.add(selected); // 장바구니에 추가
                }

                System.out.println("장바구니에 담겼습니다.");
                break;
            } else {
                System.out.println("잘못된 메뉴 번호입니다. 다시 선택해주세요.");
            }
        }
    }

    // 장바구니 메뉴 처리 함수
    public static void CartMenu(Scanner scanner, List<Coffee> cart, CoffeeDAO dao) {
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        while (true) {
            System.out.println("============ [장바구니] ============");
            for (int i = 0; i < cart.size(); i++) {
                Coffee item = cart.get(i);
                System.out.printf("%d. %s : %s x %d = %d원\n",
                    i + 1, item.getCategory(), item.getName(),
                    item.getQuantity(), item.getTotalPrice()); // 항목 출력
            }
            System.out.println("=================================");
            System.out.println("0. 수량 수정 / 삭제");
            System.out.println("1. 결제 진행");
            System.out.println("2. 이전 화면으로");
            System.out.print("번호 선택: ");
            int subChoice = scanner.nextInt();

            if (subChoice == 0) {
                System.out.print("수정할 항목 번호 선택: ");
                int idx = scanner.nextInt();

                if (idx < 1 || idx > cart.size()) {
                    System.out.println("잘못된 번호입니다.");
                    continue;
                }

                Coffee selected = cart.get(idx - 1);
                System.out.printf("%s의 새로운 수량 입력 (0 입력 시 삭제): ", selected.getName());
                int newQty = scanner.nextInt();

                if (newQty <= 0) {
                    cart.remove(idx - 1); // 삭제
                    System.out.println("항목이 삭제되었습니다.");
                } else {
                    selected.addQuantity(-selected.getQuantity()); // 기존 수량 제거 후
                    selected.addQuantity(newQty); // 새 수량 설정
                    System.out.println("수량이 수정되었습니다.");
                }

            } else if (subChoice == 1) {
                Payment(scanner, cart, dao); // 결제 처리 호출
                break;
            } else if (subChoice == 2) {
                break; // 이전 메뉴로 이동
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 결제 처리 함수
    public static void Payment(Scanner scanner, List<Coffee> cart, CoffeeDAO dao) {
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        int total = 0;
        System.out.println("============ [주문 내역] ============");
        for (Coffee item : cart) {
            System.out.printf("%s - %s x %d = %d원\n",
                item.getCategory(), item.getName(),
                item.getQuantity(), item.getTotalPrice()); // 항목 출력
            total += item.getTotalPrice();
        }
        System.out.println("==================================");
        System.out.println("총 결제 금액: " + total + "원");
        System.out.print("지불하실 금액을 입력하세요 (원): ");
        int paid = scanner.nextInt();

        if (paid < total) {
            System.out.println("금액이 부족합니다. 결제를 다시 시도해주세요.");
        } else {
            int change = paid - total;
            System.out.println("결제가 완료되었습니다. 감사합니다!");
            System.out.println("대기번호: " + waitNumber++);
            if (change > 0) {
                System.out.println("거스름돈: " + change + "원");
            }

            System.out.println("1. 처음으로 돌아가기");
            System.out.println("2. 종료하기");
            System.out.print("번호 선택: ");
            int postPay = scanner.nextInt();
            if (postPay == 2) {
                System.out.println("종료하기");
                dao.close();
                scanner.close();
                System.exit(0);
            } else {
                cart.clear(); // 장바구니 초기화 후 다시 시작
            }
        }
    }
}