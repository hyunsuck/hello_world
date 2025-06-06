package com.yedam.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MethodExe2 {

	private List<Product> store; // 필드.

	// 생성자.
	MethodExe2() {
		init();	
	}
	// 초기화(데이터)
	void init() {			
		try {
			FileInputStream fis = new FileInputStream("c:/temp/message.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			 store= (List<Product>) ois.readObject();
			 ois.close(); fis.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}// end if init.
	
//	void init1() {
//		store = new ArrayList<Product>();//new Product[10];
//		try {
//			Scanner scn = new Scanner(new FileInputStream("c:/temp/message.txt"));
//			while(true) {
//				String msg = scn.nextLine();
//				String[] msgAry = msg.split(" ");
//				store.add(new Product(msgAry[0], msgAry[1], Integer.parseInt(msgAry[2])));
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch(NoSuchElementException e) {
//			
//		}
//	}
		// 초기화 끝.

	// 종료시점에 store 정보를 message.txt에 저장.
	void save() {
		try {
			FileOutputStream fos = new FileOutputStream("c:/temp/message.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(store); // ArrayList<Product>()
			oos.flush();
			oos.close(); fos.close();
					
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	
//	void save1() {
//		try {
//			Writer writer = new FileWriter("c:/temp/message.txt");
//			for(Product prod : store) {
//				String msg = prod.getProductCode() + " " + prod.getProductName() + " " + prod.getPrice();
//				writer.write(msg + "\n");
//				writer.flush();
//			}
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	// 메소드.
	boolean add(Product prd) {
		boolean result = store.add(prd);
		return result;
	} // end of add(Product prd)

	// 상품이름, ALL
	List<Product> productList(Product prd) {
		List<Product> list = new ArrayList<Product>();//new Product[10];
		for (int i = 0; i < store.size(); i++) {
				if (prd.getProductName().equals("ALL") //
						|| store.get(i).getProductName()//
								.equals(prd.getProductName())) {
					// 상품가격이 조건으로 추가됨.
					if (store.get(i).getPrice() >= prd.getPrice()) {
						list.add(store.get(i));
					}
				}
			
		}
		return list;
	} // end of productList.

	// 삭제 => boolean remove(String code)
	boolean remove(String code) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getProductCode().equals(code)) {
				store.remove(i);
				return true;
			}
		}
		return false;
	} // end of remove.

	// 수정 => boolean modify(Product prod)
	boolean modify(Product prod) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getProductCode().equals(prod.getProductCode())) {
				// 상품명수정.
				if (prod.getProductName() != null) {
					store.get(i).setProductName(prod.getProductName());
				}
				// 상품가격수정.
				if (prod.getPrice() != 0) {
					store.get(i).setPrice(prod.getPrice());
				}
				return true;
			}
		}
		return false;
	} // end of modify.
}
