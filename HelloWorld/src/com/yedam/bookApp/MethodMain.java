package com.yedam.classes;

public class MethodMain {
    
    void method1() {
        MethodExe1 m1 = new MethodExe1();
        // System.out.println(m1);
        int n = 5;
        m1.printString(n, "*");
        
        int sum = m1.sum(n, 10);
        System.out.println(sum);
        
        double result = m1.sum(n, 10.5);
        System.out.println(result);
        
        sum = m1.sum(new int[] { 10, 20, 30 });
        System.out.println(sum);
        
        result = m1.sum(new double[] { 10.4, 20.3, 30.8 });
        System.out.println(result);
    } // end of method1 methods
    
    void method2() {
     // 상품코드: M001, 상품명: 만년필, 가격: 10000
        MethodExe2 m2 = new MethodExe2();
        if(m2.add(new Product("M001", "만년필", 10000))) {
            System.out.println("등록성공");
        }
        
        Product search = new Product();
        search.setProductName("지우개");
        
        Product[] list = m2.productList(search);
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(list[i].showList());
            }
        }
        
        if(m2.remove("A001")) {
            System.out.println("삭제성공");
        }
        
        search.setProductName("ALL");
        
        list = m2.productList(search);
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(list[i].showList());
            }
        }
        
        if (m2.modify(new Product("B001", "형광펜", 0))) {
            System.out.println("수정성공");
        }
        
        search.setProductName("지우개");
        search.setPrice(700);
        
        list = m2.productList(search);
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(list[i].showList());
            }
        }
    } // end of method2 methods
    
    public static void main(String[] args) {
        MethodExe3 m3 = new MethodExe3();
        System.out.println(m3.gugudan(7));
        System.out.println(m3.gugudan(3, 5));
        
        System.out.println();
        
        m3.printStar(5, "*");
        System.out.println();
        
        m3.printReverseStar(5, "*");
        System.out.println();
        
        m3.printPyramidStar(5, "*");
        System.out.println();
        
        m3.printDiamondStar(5, "*");
        System.out.println();
        
        m3.printCard();
        System.out.println();
        
        System.out.println("end of program");
    } // end of main methods
}
