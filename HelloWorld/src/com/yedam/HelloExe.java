package com.yedam;

import com.yedam.bookApp.Book;

public class HelloExe {
	//기능(함수) => main.메소드
	public static void main(String[] args) {
			// 1. 32000 변수 선언과 할당.
			int num = 32000;
			// 2. 34, 32, 88, 23
			int nums[] = {34,32,88,23};
			// 3. 문자 : 32
			String n= "32"; 
			//4. 정수변수에 저장.
			num = Integer.parseInt(n);
			//5. Hello, Nice, Good
			String[] strAry = {"Hello", "Nice", "Good"};
			//6. 정수 5개 저장.
			//Math.random() -> 60 ~ 100
			int[] randomNums = new int[5];
	        for (int i = 0; i < randomNums.length; i++) {
	            randomNums[i] = (int) (Math.random() * 41) + 60; // 60 ~ 100 범위
	        }
			//7.이름, 연락처, 나이 선언.
			// 홍길동, 010-1234-1234,20
	        class Student {
	            String name;
	            String phone;
	            int age;
	            Student(String name, String phone, int age){
	            	this.name = name;
	            	this.phone = phone;
	            	this.age = age;
	            }
	        }
	        
	        Student stud = new Student("홍길동", "010-1234-1234",20);
	        
	        //8 [3]
			// 홍길동, 010-1234-1234,20
			// 김민식, 010-2222-2222, 22
			// 최문식, 010-3333-3333, 23
	        Student[]stu =new Student [3];
	        stu[0]=new Student("홍길동", "010-1234-1234",20);
	        stu[1]=new Student("김민식", "010-2222-2222", 22);
	        stu[2]=new Student("최문식", "010-3333-3333",20);
			
			//9. 나이가 제일 많은 사람의 이름을 출력.
	        //for으로 나이를 가지고오고 제일 많은 사람 비교를 해서 이름을 출력
	        int age = 0;
	        String old ="";
	        for(int i=0; i<stu.length; i++) {
	        	if(stu[i].age > age ) {
	        		age = stu[i].age;
	        		old = stu[i].name;
	        	}
	        }
	        System.out.println(old);
	   
	        
	    System.out.println("Hello, World");
		
		String name;
		name = "최현석";
		
		System.out.println("이름은 " + name);
		
		int score = 100;
		
		System.out.println("점수는 " + score + "점 입니다");
		System.out.println("수정된 부분");
	}
}
