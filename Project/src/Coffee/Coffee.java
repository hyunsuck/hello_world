package Coffee;

/**
 * Coffee 클래스는 하나의 메뉴 항목(커피, 스무디, 티 등)을 표현하는 VO (Value Object) 클래스입니다.
 * 메뉴의 ID, 카테고리, 이름, 가격, 주문 수량 정보를 포함하고 있습니다.
 */
public class Coffee {
    private int id;          // 메뉴 ID (기본키)
    private String category; // 메뉴 카테고리 (예: 커피, 스무디, 티)
    private String name;     // 메뉴 이름 (예: 아메리카노)
    private int price;       // 메뉴 가격
    private int quantity;    // 주문 수량 (기본값 0)

    // 생성자: 메뉴 정보를 받아서 객체 생성
    public Coffee(int id, String category, String name, int price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = 0; // 초기 수량은 0
    }

    // getter 메서드들: 각 필드 값을 외부에서 읽을 수 있도록 함
    public int getId() { 
    	return id; 
    }

    public String getCategory() { 
    	return category; 
    }

    public String getName() { 
    	return name; 
    }

    public int getPrice() { 
    	return price; 
    }

    public int getQuantity() { 
    	return quantity; 
    }

    // 주문 수량 증가 메서드
    public void addQuantity(int count) {
        this.quantity += count;
    }

    // 현재 수량 * 단가 = 총 금액 계산
    public int getTotalPrice() {
        return price * quantity;
    }
}
