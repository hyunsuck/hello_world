package com.yedam.classes;

import java.util.Scanner;

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
    	// 기능(함수) => main메소드.
    	MethodMain mm =  new MethodMain();
    	mm. main(args); //public
    	mm.officeApp(); //default.
    
    }// end of main
    
    static void officeApp() {
    	Scanner scanner = new Scanner(System.in);
    	MethodExe2 m2 = new MethodExe2(); //기능정의.
    	
    	//사용자입력 받아서 1.목록,2.추가,3.수정,4.삭제 9.종료 구현
    	//입력메시지 정의 구현.
    	while(true) {
    		System.out.println("=================================");
    		System.out.println("1.목록 2.추가 3.수정 4.삭제 9.종료 구현");
    		System.out.println("=================================");
    	    System.out.println("메뉴를 선택하세요.");
    	    
    	    int menu = scanner.nextInt();
            scanner.nextLine();
            
            switch (menu) {
            case 1:
                // 상품 목록 조회
                System.out.print("조회할 상품명을 입력하세요 : ");
                String searchName = scanner.nextLine();
                Product searchProduct = new Product("", searchName, 0);
                Product[] productList = m2.productList(searchProduct);
                
                if (searchName.isEmpty()) {
                    System.out.println("해당 상품이 없습니다.");
                } else {
                    for (Product product : productList) {
                    	if(product != null) {
                        System.out.println(product.showList());
                    	}
                    }
                }
                break;
                
            case 2:
                // 상품 추가
                System.out.print("상품 코드 입력: ");
                String code = scanner.nextLine();
                System.out.print("상품명 입력: ");
                String name = scanner.nextLine();
                System.out.print("상품 가격 입력: ");
                int price = scanner.nextInt();
                
                if (m2.add(new Product(code, name, price))) {
                    System.out.println("상품 등록 성공!");
                } else {
                    System.out.println("상품 등록 실패! (저장 공간 부족)");
                }
                break;
                
            case 3:
                // 상품 수정
                System.out.print("수정할 상품 코드 입력: ");
                String pc = scanner.nextLine();
                System.out.print("새 상품명 : ");
                String pn = scanner.nextLine();
                System.out.print("새 가격 : ");
                int Price = scanner.nextInt();
                
                Product Product = new Product(pc, pn.isEmpty() ? null : pn, Price == 0 ? -1 : Price);
                
                if (m2.modify(Product)) {
                    System.out.println("상품 수정 성공!");
                } else {
                    System.out.println("상품 수정 실패! ");
                }
                break;
                
            case 4:
                // 상품 삭제
                System.out.print("삭제할 상품 코드 입력: ");
                String delCode = scanner.nextLine();
                
                if (m2.remove(delCode)) {
                    System.out.println("상품 삭제 성공!");
                } else {
                    System.out.println("상품 삭제 실패! ");
                }
                break;
                
            case 9:
                System.out.println("프로그램을 종료합니다.");
                scanner.close();
                return;
                
            default:
                System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
}
    
    void method4() {
    	MethodExe4 m4 = new MethodExe4();
    	m4.main();
    }// end of method4 methods
    
    void method3() {
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
    } // end of method3 methods
}
