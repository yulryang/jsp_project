package com.team1.notice.vo;



public class NoticeVO {

	// 보안이나 프레임위크의 요청에 의해 변수를 private으로 변경한다.
	private int no;
	private String title;
	private String content;
	private String writeDate;
	private int hit;
	private String pw;
	private String endDate;
	private String startDate;

	
	//private 변수의 데이터를 받아가는 getter을 만든다.
	public int getNo() { return no;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getWriteDate() {return writeDate;}
	public int getHit() {return hit;}
	public String getPw() {return pw;}
	public String getEndDate() {return endDate;}
	public String getStartDate() {return startDate;}
	//private 변수의 데이터를 수정하는(넣어주는) setter를 만든다.
	public void setNo(int no) {this.no=no;}
	public void setTitle(String title) {this.title=title;}
	public void setContent(String content) {this.content=content;}
	public void setWriteDate(String writeDate) {this.writeDate=writeDate;}
	public void setHit(int hit) {this.hit=hit;}
	public void setPw(String pw) {this.pw=pw;}
	public void setEndDate(String endDate) {this.endDate=endDate;}
	public void setStartDate(String startDate) {this.startDate=startDate;}
//	@Override
//	public String toString() {
//		return "NoticeVO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", writeDate="
//				+ writeDate + ", hit=" + hit + ", pw=" + pw + "]";
//	}
//	
	
	
	
}
