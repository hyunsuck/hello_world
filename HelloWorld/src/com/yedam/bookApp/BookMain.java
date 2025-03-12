package com.yedam.bookApp;

import java.util.Scanner;

/*
 * 등록, 수정, 삭제, 목록 
 */

public class BookMain {

	// 저장공간.
	static Book[] bookStore = new Book[100];
	static Scanner scn = new Scanner(System.in);

	// 순번생성.
	public static int getSequnceNo() {
		int max = 0;
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null //
					&& bookStore[i].getOrderNo() > max) {
				max = bookStore[i].getOrderNo();
			}
		}
		return max + 1; // 현재 마지막번호 + 1;
	} // end of getSequnceNo().

	// 등록.
	// 1. 이미 존재하는 제목은 입력불가.
	public static void add() {

		System.out.print("제목입력>> ");
		String title = scn.nextLine();
		if (searchBook(title) != null) {
			System.out.println("이미 등록된 제목입니다.");
			return;
		}

		System.out.print("저자입력>> ");
		String author = scn.nextLine();
		System.out.print("출판사입력>> ");
		String company = scn.nextLine();
		System.out.print("금액입력>> ");
		String price = scn.nextLine();
		// 입력항목을 확인.
		if (title.isBlank() || author.isBlank() || company.isBlank() || price.isBlank()) {
			System.out.println("항목을 입력하세요.");
			return; // 메소드 종료.
		}
		// Book 데이터를 생성.
		Book book = new Book(title, author, company, Integer.parseInt(price), getSequnceNo());
		// 배열에 추가.
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] == null) {
				bookStore[i] = book;
				System.out.println("등록되었습니다.");
				break;
			}
		}
	} // end of add().

	// 수정.
	public static void edit() {
		// 책제목을 입력하지 않으면 메소드 종료하는 방식.
		System.out.print("제목입력>> ");
		String title = scn.nextLine();
		if (title.isBlank()) {
			System.out.println("책제목을 반드시 입력.");
			return;
		}

		System.out.print("저자입력>> ");
		String author = scn.nextLine();
		System.out.print("출판사입력>> ");
		String company = scn.nextLine();
		System.out.print("금액입력>> ");
		String price = scn.nextLine();
		// 조회 및 수정.
		boolean isExist = false;
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				// 항목수정.
				if (!author.isBlank()) {
					bookStore[i].setAuthor(author);
				}
				if (!company.isBlank()) {
					bookStore[i].setCompany(company);
				}
				if (!price.isBlank()) {
					bookStore[i].setPrice(Integer.parseInt(price));
				}
				isExist = true;
				System.out.println("수정완료.");
			}
		}
		// 찾는 책이 없을 경우에 메세지.
		if (!isExist) {
			System.out.println("찾을 수 없습니다.");
		}
	} // end of edit().

	public static void delete() {
		// 책제목을 입력하지 않으면 반드시 값을 입력받는 방식.
		String title = "";
		while (true) {
			System.out.print("제목입력>> ");
			title = scn.nextLine();
			if (!title.isBlank()) { // 제목을 입력한 경우에..
				break;
			}
			System.out.println("제목을 입력하세요!!!");
		}
		// 삭제.
		boolean isExist = false;
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				bookStore[i] = null;
				System.out.println("삭제완료.");
				isExist = true;
				break;
			}
		}
		// 찾는 책이 없을 경우에 메세지.
		if (!isExist) {
			System.out.println("찾을 수 없습니다.");
		}
	} // end of delete().

	public static void list() {
		// 순번정렬.
		// 순번1 > 순번2, 제외:순번2(null), 순번1(null)
		Book temp = null;
		for (int i = 0; i < bookStore.length - 1; i++) {
			for (int j = 0; j < bookStore.length - 1; j++) {
				if (bookStore[j + 1] == null) { // 변경X
					continue;
				}
				if (bookStore[j] == null // 변경O
						|| bookStore[j].getOrderNo() > bookStore[j + 1].getOrderNo()) {
					temp = bookStore[j];
					bookStore[j] = bookStore[j + 1];
					bookStore[j + 1] = temp;
				}
			}
		} // end of for.

		int seqNo = 1;
		System.out.println("순번 제목      저자   가격");
		System.out.println("====================");
		for (Book bok : bookStore) {
			if (bok != null)
				System.out.println(seqNo++ + " " + bok.showList());
		}
	} // end of list().

	public static void bookInfo() {
		// 반드시 값을 입력받도록.
		String title = "";
		while (true) {
			System.out.print("제목입력>> ");
			title = scn.nextLine();
			if (!title.isBlank()) { // 제목을 입력한 경우에..
				break;
			}
			System.out.println("제목을 입력하세요!!!");
		}
		// 상세조회.
		Book result = searchBook(title);
		if (result == null) {
			System.out.println("조회결과가 없습니다.");
			return;
		}
		System.out.println(result.showBookInfo());

	} // end of bookInfo().

	// 도서명으로 조회하는 기능.
	public static Book searchBook(String title) {
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null //
					&& bookStore[i].getTitle().equals(title)) {
				return bookStore[i]; // 조회결과가 있을 경우에는 Book반환.
			}
		}
		return null; // 조회결과가 없을 경우에는 null을 반환.
	} // end of searchBook(String title).

	public static void main(String[] args) {
		init();
		boolean run = true;
		while (run) {
			System.out.println("1.도서등록 2.수정 3.삭제 4.목록 5.상세조회 9.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(scn.nextLine());
			switch (menu) {
			case 1: // 등록.
				add();
				break;
			case 2: // 수정. 도서명으로 검색, 금액을 수정.
				edit();
				break;
			case 3: // 삭제. 도서명으로 검색 후 삭제.
				delete();
				break;
			case 4: // 목록.
				list();
				break;
			case 5: // 상세조회.
				bookInfo();
				break;
			case 9: // 종료.
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
		}
		System.out.println("end of prog.");
	} // end of main().

	public static void init() {
		bookStore[0] = new Book("이것이자바다", "신용권", "한빛출", 20000, 1);
		bookStore[1] = new Book("스크립트기초", "박기초", "우리출", 26000, 2);
		bookStore[2] = new Book("HTML,CSS", "김하늘", "가람출", 25000, 3);
	}
}
