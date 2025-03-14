package com.yedam.inheritance;
/*
 * Friend 상속.
 */
public class UnivFriend extends Friend {
	//필드.
	private String univName;
	private String major;
	//생성자.
	public UnivFriend() {
		super();
	}
	public UnivFriend(String name, String tel, String univName, String major) {
		super(name, tel);
		this.univName = univName;
		this.major = major;
	}
	// 부모의 메소드 -> 자식이 재정의. overriding
	@Override
	public String toString() {
		return  super.toString() + ", 학교는 " + getUnivName() + ", 학과는 " + getMajor();
	}
	@Override
	public String getName() {
		return super.getName();
	}

//	@Override
//	public void setName(String name) {
//		// 부모 final 메소드는 overiding 안됨.
//	}
	//getter, setter.
	public String getUnivName() {
		return univName;
	}
	public void setUnivName(String univName) {
		this.univName = univName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
}