package com.yedam.api;

import java.io.IOException;

public class StringExe {
	public static void main(String[] args) throws IOException {
		String str = "Hello, World";
		str = new String("Hello, World");
		byte[] bAry = str.getBytes();
		for(int i=0; i< bAry.length; i++) {
			System.out.println(bAry[i]);
		}
		
		byte[] bstr = {72,101,108,108,111,44,32,87,111,114,108,100};
		String msg =  new String(bstr, 7 ,5);
		System.out.println(msg);
		char result = msg.charAt(0);
		System.out.println((int) result);
		
		// String: "", char: ''
		if(result == 'W') {
			//비교구문.
		}
		
		int idx = msg.indexOf("o");
		if(idx != -1) {
			//처리.
		}
		
		String[] names = {"홍길동","홍길승","김민규"};
		int cnt = 0;
		for(int i=0; i< names.length; i++) {
			if(names[i].indexOf("홍") == 0) {
				cnt++;
			}
		}
		System.out.println("\"홍\"을 포함하는 이름의 갯수: " + cnt);
		
		System.out.println("Hello, World".substring(3,7));
		
		byte[] bytes = new byte[100];
		
		System.out.println("입력: ");
		int readByteNo = System.in.read(bytes);
		
		String str1 = new String (bytes,0, readByteNo-2);
		System.out.println(str1);
	}
}
