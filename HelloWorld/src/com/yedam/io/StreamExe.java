package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamExe {
	public static void main(String[] args) {
		// 입력+출력 -> 복사
		try {
			InputStream is = new FileInputStream("c:/temp/image.PNG");
			OutputStream os = new FileOutputStream("c:/temp/image3.PNG");
			
			byte[] buf = new  byte[100];  		
			while(true) {
				int data = is.read(buf);
				if(data == -1) {
					break;
				}
				os.write(data);
			}
			is.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("end of prog.");
	} // end of main.
	
	static void read() {
	//입력스트림(바이트)
			try {
				InputStream is = new FileInputStream("c:/temp/data.bin");
				while (true) {
					int data = is.read();
					if(data == -1) {
						break;
					}
				System.out.println(data);
				}
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	static void write() {
	// 출력스트림(바이트)
			try {
				OutputStream os = new FileOutputStream("c:/temp/data.bin");
				os.write(10);
				os.write(20);
				os.write(30);
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("end of prog.");
	}
}
