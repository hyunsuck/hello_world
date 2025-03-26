package Coffee; // 패키지 선언

// 메뉴 객체를 표현하는 클래스
public class Coffee {
    private int id;             // 메뉴 고유 ID
    private String name;        // 메뉴 이름
    private int price;          // 메뉴 가격
    private String category;    // 카테고리 (커피, 티, 스무디 등)
    private int quantity;       // 장바구니에서 선택된 수량

    public Coffee() {} // 기본 생성자

    // 전체 필드를 초기화하는 생성자
    public Coffee(int id, String name, int price, String category) {
        this.id = id; // ID 설정
        this.name = name; // 이름 설정
        this.price = price; // 가격 설정
        this.category = category; // 카테고리 설정
        this.quantity = 0; // 초기 수량은 0
    }

    // 총 가격 계산 (가격 * 수량)
    public int getTotalPrice() {
        return price * quantity;
    }

    // 수량 누적 증가
    public void addQuantity(int qty) {
        this.quantity += qty;
    }

    // getter / setter 메서드

    public int getId() { // ID 반환
        return id;
    }

    public String getName() { // 이름 반환
        return name;
    }

    public int getPrice() { // 가격 반환
        return price;
    }

    public String getCategory() { // 카테고리 반환
        return category;
    }

    public int getQuantity() { // 수량 반환
        return quantity;
    }

    public void setId(int id) { // ID 설정
        this.id = id;
    }

    public void setName(String name) { // 이름 설정
        this.name = name;
    }

    public void setPrice(int price) { // 가격 설정
        this.price = price;
    }

    public void setCategory(String category) { // 카테고리 설정
        this.category = category;
    }

    public void setQuantity(int quantity) { // 수량 설정
        this.quantity = quantity;
    }
}
