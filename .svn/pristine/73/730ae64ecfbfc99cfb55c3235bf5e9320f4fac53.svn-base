package com.team1.member.vo;
/*
 * 로그인 정보를 담아 놓는 객체
 */
public class LoginVO {
	
	private String id, pw, name;
	private int gradeNo;
	private String gradeName;
	
	//생성자를 통해서 데이터를 셋팅하고 중간에 변경해서 사용할 수 없다.
	public LoginVO(String id, String pw, String name, 
			int gradeNo, String gradeName) {
		super(); //부모 클래스 (Object) 기본 생성자를 호출하는 경우 생략가능.
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gradeNo = gradeNo;
		this.gradeName = gradeName;
	}
	//사용자가 로그인 처리를 위해서 id와 pw를 입력할 수 있도록 하는 기본 생성자
	public LoginVO() {};
	
	
	
	//사용자가 id와 pw를 입력하면 변경하능하게 하기 위해 setter작성.
	//나머지는 데이터를 받아가는 getter만 작성 ->중간에 데이터 변경 금지
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public String getGradeName() {
		return gradeName;
	}
	
	
	//데이터 확인용 -> Object의 toString() 재정의해서 사용
/*	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", name=" + name + ", gradeNo=" + gradeNo + ", gradeName="
				+ gradeName + "]"; 
	} */
	
	
	
}
