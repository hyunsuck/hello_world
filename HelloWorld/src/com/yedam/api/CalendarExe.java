package com.yedam.api;

import java.util.Calendar;

public class CalendarExe {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(); // 2025-03-18
		cal.set(Calendar.YEAR, 2024); //2024년
		cal.set(Calendar.MONTH, 1); //2월
		cal.set(Calendar.DATE, 10); //10일
		cal.set(2023, 2, 5); // 2023년 3월 5일.
		
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.DATE));
		System.out.println(cal.get(Calendar.DAY_OF_WEEK)); //요일.
		System.out.println(cal.getActualMaximum(Calendar.DATE)); //마지막날.
		
		// 년, 월 입력 => 1일의 요일 반환. 말일을 반환.
		System.out.println("요일정보: " + getDay(2025, 7)); //7월달의 1일의 요일
		System.out.println("말일정보: " + getLastDate(2025, 7)); // 7월달의 마지막날
	}
	
	static String getDay(int year, int month) {
		Calendar today = Calendar.getInstance();
		today.set(year, month -1,1);
		int day = today.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.SUNDAY:
            return "일요일";
        case Calendar.MONDAY:
            return "월요일";
        case Calendar.TUESDAY:
            return "화요일";
        case Calendar.WEDNESDAY:
            return "수요일";
        case Calendar.THURSDAY:
            return "목요일";
        case Calendar.FRIDAY:
            return "금요일";
        case Calendar.SATURDAY:
            return "토요일";
		}
		return "토요일";
	}
	
	static int getLastDate(int year, int month) {
		Calendar today = Calendar.getInstance();
        today.set(year, month - 1, 1); // 해당 연도와 월 설정
        return today.getActualMaximum(Calendar.DATE); // 마지막 날짜 반환
    }
}
