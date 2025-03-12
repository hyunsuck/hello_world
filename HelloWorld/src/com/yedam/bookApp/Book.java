package com.yedam.bookApp;

public class Book {
    private static int orderCount = 1; // 주문 번호 자동 증가

    private int orderno; // 주문 번호
    private String title;
    private String author;
    private String company;
    private int price;

    public Book(String title, String author, String company, int price, int orderno) {
        this.orderno = orderCount++; // 주문 번호 자동 생성
        this.title = title;
        this.author = author;
        this.company = company;
        this.price = price;
    }

    public int getOrderNo() {
        return orderno;
    }
	//메소드.
	public String showList() {
		return title + " " + author + " " + price;
	}
	public String showListWithNo() {
		return orderno + " " + title + " " + author + " " + price;
	}
	public String showBookInfo() {
		String msg="제목은 " + title + "/ 저자는 " + author + "\n출판사는 " + company + "/ 가격은" + price;
		return msg;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getCompany() {
		return company;
	}
	public int getPrice() {
		return price;
	}
	public void setTitle(String title) {
		this.title =title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setPrice(int price) {
		this.price = price;
	}
//	public void setBook(String title2, String author2, String company2, int price2) {
		
	}


