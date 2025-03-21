package co.yedam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmpDAO {
    // 컬렉션 저장소
    private List<Employee> empList;
    private Scanner scn;

    // 생성자
    public EmpDAO() {
        empList = new ArrayList<>();
        scn = new Scanner(System.in);
    }

    // ======================== 주요 기능 메서드 ========================

    // 사원 등록
    private void add() {
        System.out.print("사번입력>> ");
        String number = scn.nextLine();
        if (searchByNumber(number) != null) {
            System.out.println("이미 등록된 사번입니다.");
            return;
        }

        System.out.print("이름입력>> ");
        String name = scn.nextLine();
        System.out.print("전화번호입력>> ");
        String phone = scn.nextLine();
        System.out.print("입사일입력>> ");
        String date = scn.nextLine();
        System.out.print("급여입력>> ");
        String salary = scn.nextLine();

        if (number.isBlank() || name.isBlank() || phone.isBlank() || date.isBlank() || salary.isBlank()) {
            System.out.println("항목을 모두 입력하세요.");
            return;
        }

        try {
            int sal = Integer.parseInt(salary);
            Employee emp = new Employee(number, name, phone, date, sal);
            if (add(emp)) {
                System.out.println("등록 완료");
            } else {
                System.out.println("등록 실패");
            }
        } catch (NumberFormatException e) {
            System.out.println("급여는 숫자로 입력해야 합니다.");
        }
    }

    // 목록 출력
    private void list() {
        System.out.println("사번    이름   전화번호");
        for (Employee e : empList) {
            System.out.println(e.showList());
        }
    }

    // 급여 수정
    private void edit() {
        System.out.print("사번 급여>> ");
        String[] parts = scn.nextLine().split(" ");
        if (parts.length != 2) {
            System.out.println("입력 형식이 잘못되었습니다.");
            return;
        }
        try {
            String number = parts[0];
            int newSalary = Integer.parseInt(parts[1]);
            if (edit(number, newSalary)) {
                System.out.println("수정 완료");
            } else {
                System.out.println("해당 사원이 없습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("급여는 숫자로 입력하세요.");
        }
    }

    // 삭제
    private void delete() {
        System.out.print("사번 입력>> ");
        String number = scn.nextLine();
        if (delete(number)) {
            System.out.println("삭제 완료");
        } else {
            System.out.println("해당 사원이 없습니다.");
        }
    }

    // 입사일 조회
    private void listByDate() {
        System.out.print("입사일자>> ");
        String inputDate = scn.nextLine().trim();

        if (empList.isEmpty()) {
            System.out.println("등록된 사원이 없습니다.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate targetDate = LocalDate.parse(inputDate, formatter);

            // 입사일이 targetDate 이상인 사원만 필터링
            List<Employee> result = empList.stream()
                .filter(emp -> {
                    LocalDate empDate = LocalDate.parse(emp.getDate(), formatter);
                    return !empDate.isBefore(targetDate); // targetDate 이상
                })
                .sorted(Comparator.comparing(emp -> LocalDate.parse(emp.getDate(), formatter)))
                .toList();

            if (result.isEmpty()) {
                System.out.println("해당 입사일 이후 입사한 사원이 없습니다.");
            } else {
                result.forEach(emp -> System.out.println(emp.showListdate()));
            }

        } catch (Exception e) {
            System.out.println("날짜 형식이 잘못되었습니다.");
        }
    }

    

    // ======================== 내부 로직 처리 메서드 ========================

    private boolean add(Employee emp) {
        if (searchByNumber(emp.getNumber()) != null) {
            return false;
        }
        empList.add(emp);
        return true;
    }

    private boolean edit(String number, int newSalary) {
        Employee emp = searchByNumber(number);
        if (emp != null) {
            emp.setSalary(newSalary);
            return true;
        }
        return false;
    }

    private boolean delete(String number) {
        Employee emp = searchByNumber(number);
        if (emp != null) {
            empList.remove(emp);
            return true;
        }
        return false;
    }

    private Employee searchByNumber(String number) {
        for (Employee emp : empList) {
            if (emp.getNumber().equals(number)) {
                return emp;
            }
        }
        return null;
    }


    // ======================== 실행 메뉴 ========================

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println("1.등록 2.목록 3.수정(급여) 4.삭제 5.조회(입사일) 6.종료");
            System.out.print("선택>> ");

            int menu = 0;
            try {
                menu = Integer.parseInt(scn.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력하세요.");
                continue;
            }

            switch (menu) {
                case 1:
                    add();
                    break;
                case 2:
                    list();
                    break;
                case 3:
                    edit();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    listByDate();
                    break;
                case 6:
                    System.out.println("프로그램 종료");
                    run = false;
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}
