package com.yedam.variable;

import java.util.Scanner;

public class VarExe6 {
	public static void test(String[] args){
	// 임의의 정수 1 ~ 50 임의의 값.
	//for (int i =1; i<=5; i++) 5가지 출력
	//	System.out.println((int) (Math.random() * 50) + 1); // 1 <= x < 51
		
	// 학생의 점수 (30~100 사이의 점수)
	// 정수(int) -> 5개 저장.
	//int[] scores = new int[5];
	//for (int i=0; i<scores.length; i++){
	// scores[i] = (int)(Math.random()*71) + 30;
    // }
	//for (int i =0; i < scores.length; i++) {
	//  if(scores[i] % 2 == 1) {
	//  System.out.println(scores[i]);
		   
		// 홀수의 값을 출력.
		int[] scores = new int[500];
			for (int i=0; i<scores.length; i++){
				scores[i] = (int)(Math.random()*71) + 30;
			}
				for (int i =0; i < scores.length; i++) {
					if(scores[i] == 100) {
						System.out.println( i + "번째 값: " +scores[i]);
					}
				}
	} // end of test().
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		Member m1 = new Member(); //인스턴스생성.
		m1.setName("홍길동"); // setter
		System.out.println(m1.getScore());
		Member m2 = new Member(); //인스턴스생성.
		m2.setName("최민수");
		Member m3 = new Member(); //인스턴스생성.
		m3.setName("김민수");
		Member m4 = new Member(); //인스턴스생성.
		m4.setName("박인만");
		
		//배열.
		Member[] members = {m1,m2,m3,m4};
		// 70 ~ 100 사이의 임의값으로 점수지정.
		for (int i = 0; i<members.length; i++) {
			members[i].setScore((int)(Math.random()*31) + 70);
		}
			
		//조회이름을 입력 -> 점수출력	
		System.out.println("조회할 이름 입력>>");
		String search = scn.nextLine();
			
		//for 반복문 활용. 30 == 30, members[i].name.equals(search)
        for (int i = 0; i < members.length; i++) {
            if (members[i].getName().equals(search)) {
                System.out.println(search + "님의 점수: " + members[i].getScore());
                break; 
            }
        }
			
		//점수가 가장 높은 사람의 이름.
//			int max = members[0].score;
//			String name= members[0].name;
//				for(int i = 0; i<members.length; i++) {
//					System.out.println(members[i].name+","+members[i].score);
//					if(max < members[i].score) {
//					   max = members[i].score;
//					   name = members[i].name;
//					}
//				}
//				System.out.println("최고점수는 " + name);
        scn.close();
	} // end of main()
}

