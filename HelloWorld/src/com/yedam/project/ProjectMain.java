package com.yedam.project;

import java.util.List;
import java.util.Scanner;



public class ProjectMain {

	// 2. 정적필드. 할당.
		private static ProjectMain instance = new ProjectMain();

		// 1. 생성자 private 선언.
		private ProjectMain() {
		}

		// 3. getInstance() 제공.
		public static ProjectMain getInstance() {
			return instance;
		}
		// 스캐너 객체선언.
		Scanner scn = new Scanner(System.in);
		//jdbc 처리.
//		ProjectJdbc dao = new ProjectJdbc();  


		// 순번생성.
		private int getSequnceNo() {
			int max = 0;
			return max + 1; // 현재 마지막번호 + 1;
		} // end of getSequnceNo().

		// 등록.
		// 1. 이미 존재하는 제목은 입력불가.
		private void add() {

			System.out.print("이름입력>> ");
			String name = scn.nextLine();
			if (searchList(name) != null) {
				System.out.println("이미 등록된 이름입니다.");
				return;
			}

			System.out.print("금액입력>> ");
			String price = scn.nextLine();
			// 입력항목을 확인.
			if (name.isBlank()  || price.isBlank()) {
				System.out.println("항목을 입력하세요.");
				return; // 메소드 종료.
			}
			// Book 데이터를 생성.
			Project project = new project(name,  Integer.parseInt(price), getSequnceNo());
			// 배열에 추가. -> ojdbc 변경.
			if(dao.insert(project)) {
				System.out.println("정상등록.");
			}else {
				System.out.println("등록예외.");
			}
//			for (int i = 0; i < bookStore.length; i++) {
//				if (bookStore[i] == null) {
//					bookStore[i] = book;
//					System.out.println("등록되었습니다.");
//					break;
//				}
//			}
		} // end of add().

		// 수정.
		private void edit() {
			// 책제목을 입력하지 않으면 메소드 종료하는 방식.
			System.out.print("커피코드입력>> ");
			String bcode = scn.nextLine();
			if (bcode.isBlank()) {
				System.out.println("커피코드를 반드시 입력.");
				return;
			}

			System.out.print("이름입력>> ");
			String name = scn.nextLine();
			System.out.print("금액입력>> ");
			String price = scn.nextLine();
			
			//update(파라미터)
			Project project = new project();
			project.setCoffeeCode(ccode);
			project.setName(name);

			project.setPrice(Integer.parseInt(price));
			// 조회 및 수정.
			// 찾는 책이 없을 경우에 메세지.
			if (dao.update(project)) {
				System.out.println("수정성공.");
			}else {
				System.out.println("수정예외.");
			}
		} // end of edit().

		private void delete() {
			// 책제목을 입력하지 않으면 반드시 값을 입력받는 방식.
			String bcode = "";
			while (bcode.isBlank()) {
				System.out.print("커피코드입력>> ");
				bcode = scn.nextLine();
				if (!bcode.isBlank()) { // 제목을 입력한 경우에..
					break;
				}
				System.out.println("커피코드를 반드시 입력.");
			}
			// 찾는 책이 없을 경우에 메세지.
			if (dao.delete(ccode)) {
				System.out.println("삭제성공.");
			}else {
				System.out.println("삭제실패.");
			}
		} // end of delete().

		private void list() {

			int seqNo = 1;
			System.out.println("순번 코드 제목   가격");
			System.out.println("===============");
			List<Projcet> list = searchList("");
			for (Projcet pok : list) {
				if (pok != null)
					System.out.println(seqNo++ + " " + pok.showList());
			}
		} // end of list().

		// list와 listCompany에서 활용할 공통메소드.
		private List<Project> searchList(String keyword) {
			List<Project> list = dao.list(keyword);
			return list;
		} // end of searchList.

		private void ProjcetInfo() {
			// 반드시 값을 입력받도록.
			String name = "";
			while (true) {
				System.out.print("이름입력>> ");
				name = scn.nextLine();
				if (!name.isBlank()) { // 제목을 입력한 경우에..
					break;
				}
				System.out.println("제목을 입력하세요!!!");
			}
			// 상세조회.
			Projcet result = searchProjcet(name);
			if (result == null) {
				System.out.println("조회결과가 없습니다.");
				return;
			}
			System.out.println(result.showBookInfo());

		} // end of bookInfo().

		// 도서명으로 조회하는 기능.
		private Projcet searchProjcet(String ccode) {
			return dao.select(ccode); //조회결과가 없을 경우에는 null을 반환.
		

		public void main(String[] args) {


			boolean run = true;
			while (run) {
				System.out.println("1.메뉴등록 2.수정 3.삭제 4.목록 9.종료");
				System.out.print("선택>> ");
				// 예외처리.
				int menu = 9;
				while (true) {
					try {
						menu = Integer.parseInt(scn.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.out.println("정수값을 입력하세요.");
					}
				}

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


