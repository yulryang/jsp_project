package com.team1.member.vo;
// 변수 저장 클래스
public class MemberVO {	// VO: valuable object

	// private 사용 이유: 보안이나 프레임워크의 요청
	private String id;
	private String pw;
	private String name;
	private int age;
	private String tel;
	private int gradeNo;
	private String gradeName;
	private String photo;
	private String regDate;
	private String state;
	private String newPw;
	
	//생성자를 통해서 데이터를 셋팅하고 중간에 변경해서 사용할 수 없다.
		public MemberVO(String id, String pw, String name, 
				int gradeNo, String gradeName) {
			super(); //부모 클래스 (Object) 기본 생성자를 호출하는 경우 생략가능.
			this.id = id;
			this.pw = pw;
			this.name = name;
			this.gradeNo = gradeNo;
			this.gradeName = gradeName;
		}
		//사용자가 로그인 처리를 위해서 id와 pw를 입력할 수 있도록 하는 기본 생성자
		public MemberVO() {};
	
	// getter, setter
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}

	public String getPw() {return pw;}
	public void setPw(String pw) {this.pw = pw;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	public String getTel() {return tel;}
	public void setTel(String tel) {this.tel = tel;}

	public int getGradeNo() {return gradeNo;}
	public void setGradeNo(int gradeNo) 
		{this.gradeNo = gradeNo;}
	
	public String getGradeName() {return gradeName;}
	public void setGradeName(String gradeName) 
		{this.gradeName = gradeName;}

	public String getPhoto() {return photo;}
	public void setPhoto(String photo) 
		{this.photo = photo;}

	public String getRegDate() {return regDate;}
	public void setRegDate(String regDate) 
		{this.regDate = regDate;}

	public String getState() {return state;}
	public void setState(String state) 
		{this.state = state;}
	
	public String getNewPw() {return newPw;}
	public void setNewPw(String newPw) {this.newPw = newPw;}


/*	@Override
	public String toString() {	// toString: 변수에 넣은 데이터 확인
		return "MemberVO [id=" + id + ", pw=" + pw + 
				", name=" + name + ", age=" + age + 
				", tel=" + tel + ", gradeNo=" + gradeNo + 
				", regdate=" + regDate + 
				", state=" + state + ", newPw=" + newPw + "]";
	} */
	
}
