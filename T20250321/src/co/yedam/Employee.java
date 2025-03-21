package co.yedam;

public class Employee {
	// 필드: 사원번호, 이름, 전화번호, 입사일, 급여
	private String number;
	private String name;
	private String phone;
	private String date;
	private int salary;

	// 기본 생성자
	public Employee() {}

	// 전체 필드를 받는 생성자
	public Employee(String number, String name, String phone, String date, int salary) {
		this.number = number;
		this.name = name;
		this.phone = phone;
		this.date = date;
		this.salary = salary;
	}
	
	// Getter & Setter
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // 출력용 메서드
    public String showList() {
        return number + " " + name + " " + phone;
    }
    public String showListdate() {
    	return number + " " + name + " " + date;
    }

   @Override
    public String toString() {
        return "Employee{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", salary=" + salary +
                '}';
   }	    
}


