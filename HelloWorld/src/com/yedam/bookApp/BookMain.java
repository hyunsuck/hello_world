package com.yedam.bookApp;

import java.util.Scanner;

public class BookMain {
    private static BookMain instance = new BookMain();
    private BookMain() { initUsers(); }
    public static BookMain getInstance() { return instance; }

    private Book[] bookStore = new Book[100];
    private User[] users = new User[3]; // 회원 3명 등록
    private Scanner scn = new Scanner(System.in);

    // ⭐ 사용자 등록 (초기 회원 데이터)
    private void initUsers() {
        users[0] = new User("admin", "관리자", "1234");
        users[1] = new User("user1", "김철수", "pass1");
        users[2] = new User("user2", "이영희", "pass2");
    }

    // ⭐ 로그인 메서드
    private boolean login() {
        System.out.print("아이디 입력: ");
        String id = scn.nextLine();
        System.out.print("비밀번호 입력: ");
        String password = scn.nextLine();

        for (User user : users) {
            if (user != null && user.getUserId().equals(id) && user.getPassword().equals(password)) {
                System.out.println(user.getUserName() + "님 로그인 성공!");
                return true;
            }
        }
        System.out.println("로그인 실패. 아이디 또는 비밀번호가 올바르지 않습니다.");
        return false;
    }

    // ⭐ main() 실행 (로그인 성공 시)
    public static void main(String[] args) {
        BookMain bookMain = getInstance();
        if (!bookMain.login()) {
            System.out.println("프로그램을 종료합니다.");
            return;
        }

        bookMain.init();
        boolean run = true;
        while (run) {
            try {
                System.out.println("1.도서등록 2.수정 3.삭제 4.목록 5.상세조회 9.종료");
                System.out.print("선택>> ");
                int menu = Integer.parseInt(bookMain.scn.nextLine());

                switch (menu) {
                    case 1: bookMain.add(); break;
                    case 2: bookMain.edit(); break;
                    case 3: bookMain.delete(); break;
                    case 4: bookMain.list(); break;
                    case 5: bookMain.bookInfo(); break;
                    case 9:
                        System.out.println("프로그램을 종료합니다.");
                        run = false;
                        break;
                    default:
                        System.out.println("올바른 메뉴를 선택하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요!");
            }
        }
    }

    private void init() {
        bookStore[0] = new Book("이것이 자바다", "신용권", "한빛출판사", 20000);
        bookStore[1] = new Book("HTML+CSS 웹 디자인", "이시카와", "영진출판사", 25000);
        bookStore[2] = new Book("자바의 정석", "남궁성", "도우출판", 30000);
    }

    private void add() {
        System.out.print("제목 입력: ");
        String title = scn.nextLine();
        System.out.print("저자 입력: ");
        String author = scn.nextLine();
        System.out.print("출판사 입력: ");
        String company = scn.nextLine();
        System.out.print("가격 입력: ");
        int price = Integer.parseInt(scn.nextLine());

        for (int i = 0; i < bookStore.length; i++) {
            if (bookStore[i] == null) {
                bookStore[i] = new Book(title, author, company, price);
                System.out.println("도서 등록 완료!");
                return;
            }
        }
        System.out.println("등록 공간이 없습니다.");
    }

    private void edit() {
        System.out.print("수정할 도서 제목 입력: ");
        String title = scn.nextLine();
        
        for (Book book : bookStore) {
            if (book != null && book.getTitle().equals(title)) {
                System.out.print("새 저자 입력 (Enter: 변경 없음): ");
                String newAuthor = scn.nextLine();
                System.out.print("새 출판사 입력 (Enter: 변경 없음): ");
                String newCompany = scn.nextLine();
                System.out.print("새 가격 입력 (-1 입력 시 변경 없음): ");
                int newPrice = Integer.parseInt(scn.nextLine());

                if (!newAuthor.isBlank()) book.setAuthor(newAuthor);
                if (!newCompany.isBlank()) book.setCompany(newCompany);
                if (newPrice >= 0) book.setPrice(newPrice);

                System.out.println("도서 정보 수정 완료!");
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }

    private void delete() {
        System.out.print("삭제할 도서 제목 입력: ");
        String title = scn.nextLine();
        
        for (int i = 0; i < bookStore.length; i++) {
            if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
                bookStore[i] = null;
                System.out.println("도서 삭제 완료!");
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }

    private void list() {
        System.out.println("==== 도서 목록 ====");
        for (Book book : bookStore) {
            if (book != null) {
                System.out.println(book.showListWithNo());
            }
        }
    }

    private void bookInfo() {
        System.out.print("조회할 도서 제목 입력: ");
        String title = scn.nextLine();

        for (Book book : bookStore) {
            if (book != null && book.getTitle().equals(title)) {
                System.out.println(book.showBookInfo());
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }
	public void start() {
		// TODO Auto-generated method stub
		
	}
}
