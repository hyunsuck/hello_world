package com.yedam.project;

/*
 * 커피종류, 가격, 
 */

public class Project {

	
		// 필드.
		private String name;
		private int price;
		private int orderNo; // 1,2,3
		private String coffeecode;
		
		// 생성자.
		public Project() {}
		public Project(String name, int price) {
			this.name = name;
			this.price = price;
		}
		public Project(String name,  int price, int orderNo) {
			this( name, price); // this: 생성된 인스턴스.
			this.orderNo = orderNo;
		}
		// 메소드.
		public String showList() {
			return coffeecode + " " +name +  " " + price;
		}
		public String showListWithNo() {
			return orderNo + " " + name +  " " + price;
		}
		public String showBookInfo() {
			String msg = "커피이름은 " + name +  "/ 가격은 " + price;
			return msg;
		}
		public String getName() {
			return name;
		}
		
		public int getPrice() {
			return price;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(int orderNo) {
			this.orderNo = orderNo;
		}	
		
		public String getCoffeeCode() {
			return coffeecode;
		}
		public void setCoffeeCode(String coffeecode) {
			this.coffeecode = coffeecode;
		}
	}

 