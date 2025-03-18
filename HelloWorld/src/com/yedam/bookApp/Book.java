package com.yedam.bookApp;

public class Book {
    private static int orderCount = 1; // 주문 번호 자동 증가

    private int orderno;
    private String title;
    private String author;
    private String company;
    private int price;

    public Book(String title, String author, String company, int price) {
        this.orderno = orderCount++; // 자동 증가
        this.title = title;
        this.author = author;
        this.company = company;
        this.price = price;
    }

	public Book() {
	}

	public int getOrderNo() { return orderno; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCompany() { return company; }
    public int getPrice() { return price; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCompany(String company) { this.company = company; }
    public void setPrice(int price) { this.price = price; }

    public String showListWithNo() {
        return orderno + " " + title + " " + author + " " + price;
    }

    public String showBookInfo() {
        return "제목: " + title + "\n저자: " + author + "\n출판사: " + company + "\n가격: " + price;
    }
}


//	public void setBook(String title2, String author2, String company2, int price2) {
		
//	}


