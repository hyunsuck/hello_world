package com.yedam.classes;

public class MethodExe2 {
    private Product[] store;

    MethodExe2() {
        store = new Product[10];
        store[0] = new Product("A001", "지우개", 500);
        store[1] = new Product("B001", "샤프1000", 1000);
        store[2] = new Product("C001", "연필500", 800);
        store[3] = new Product("D001", "지우개", 1000);
    }

    boolean add(Product prd) {
        for (int i = 0; i < store.length; i++) {
            if (store[i] == null) {
                store[i] = prd;
                // System.out.println("등록완료");
                return true;
            }
        }
        
        return false;
    } // end of add methods
    
    Product[] productList(Product prd) { 
        Product[] list = new Product[10];
        int idx = 0;
        for (int i = 0; i < store.length; i++) {
            if (store[i] != null) {
                if (prd.getProductName().equals("ALL") || store[i].getProductName().equals(prd.getProductName())) {
                    if (store[i].getPrice() >= prd.getPrice())
                        list[idx++] = store[i];
                }
            }
        }
        
        return list;
    } // end of productList methods
    
    boolean remove(String code) {
        for (int i = 0; i < store.length; i++) {
            if (store[i] != null && store[i].getProductCode().equals(code)) {
                store[i] = null;
                return true;
            }
        }
        
        return false;
    } // end of remove methods
    
    boolean modify(Product prod) {
        for (int i = 0; i < store.length; i++) {
            if (store[i] != null && store[i].getProductCode().equals(prod.getProductCode())) { 
                
                // 상품명 수정
                if (prod.getProductName() != null) {
                    store[i].setProductName(prod.getProductName());
                }
                
                if (prod.getPrice() != 0) {
                    store[i].setPrice(prod.getPrice());
                }
                
                return true;
            }
        }
        
        return false;
    } // end of modify methods
}
