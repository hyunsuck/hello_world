package Coffee;

// 메뉴 객체를 표현하는 클래스
public class Coffee {
    private int id;             // 메뉴 고유 ID
    private String name;        // 메뉴 이름
    private int price;          // 가격
    private String category;    // 카테고리 (커피, 티, 스무디 등)
    private int quantity;       // 장바구니에서 사용할 수량

    public Coffee() {}

    public Coffee(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = 0;
    }

    // 총 가격 계산
    public int getTotalPrice() {
        return price * quantity;
    }

    public void addQuantity(int qty) {
        this.quantity += qty;
    }

    // getter / setter
    public int getId() { 
    	return id; 
    }
    public String getName() { 
    	return name; 
    }
    public int getPrice() { 
    	return price; 
    }
    public String getCategory() { 
    	return category; 
    }
    public int getQuantity() {
    	return quantity; 
    }

    public void setId(int id) { 
    	this.id = id; 
    }
    public void setName(String name) { 
    	this.name = name; 
    }
    public void setPrice(int price) { 
    	this.price = price; 
    }
    public void setCategory(String category) { 
    	this.category = category; 
    }
    public void setQuantity(int quantity) { 
    	this.quantity = quantity; 
    }
}
