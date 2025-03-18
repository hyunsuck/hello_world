package com.yedam.api;

public class StringUtil {
	//1. 성별
	static String getGender(String ssn) {
		// 숫자만 남기기 (공백 및 "-" 제거)
	    ssn = ssn.replaceAll("[^0-9]", ""); 
		int pos = -1;
		if (ssn.length() ==13) {
			pos = 6;
		}else if (ssn.length() == 14) {
			pos = 7;
		} else {
            return "올바르지 않은 주민등록번호"; // 예외처리 추가
        }
//		pos = ssn.length() - 7;
		char gNo = ssn.charAt(pos);
		switch (gNo) {
        case '1':
        case '3':
            return "남";
        case '2':
        case '4':
            return "여";
        default:
            return "알 수 없음"; // 오류 방지
		}
	}
	//2.파일명
	static String getExtName(String file) {
        int Indexof = file.lastIndexOf(".");
        if (Indexof == -1 || Indexof == file.length() - 1) {
            return "확장자 없음";
        }
        return file.substring(Indexof + 1);
    }
	static String getFileName(String file) {
		int pos = 0;
		String fileName = "";
		// "c:/temp/orange.jpg"
		while(true) {
			int idx = file.indexOf("/", pos); //찾을 값, 찾을 위치으 시작값.
			if(idx == -1) {
				fileName = file.substring(pos, file.indexOf("."));
				break;
			}
			pos = idx + 1;
		}
		return fileName;
	}
	
	//3. 파일확장자
	static String getExtName2(String file) {
		String fileName = "";
		fileName = file.substring(file.lastIndexOf("/" + 1, file.indexOf(".")));
		return fileName;
	}

}
