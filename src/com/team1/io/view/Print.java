package com.team1.io.view;

import static com.team1.main.controller.Main.login;

import java.util.List;

import com.team1.member.vo.MemberVO;;


/* 정보 출력 클래스 */
public class Print {
	
	// 로그인 정보 출력
	public static void printLoginInfo() {
		System.out.println("+-----------------+");
		// MainController.login static 변수를 static import
		// -> 클래스 이름 붙일 필요x
		if(login == null) {	// null : 로그인을 하지 않음
			System.out.println(" 로그인이 되어 있지 않습니다. ");
		} else {	// else: 로그인 함
			System.out.println(" 환영합니다." + login.getName() + "("
					+ login.getId() + " - " + login.getGradeName()
					+ " 권한으로 접속)");
		}
		System.out.println("+-----------------+");
	}
	
	// 회원 목록 출력
	public void list(List<MemberVO> list) {
		System.out.println("+----[회원 리스트]----+");
		System.out.println("--------------------------------------");
		System.out.println(" ID/   이름      / 나이 /  전화번호   / 등급  /"
				+ "   사진     /  가입 날짜     / 상태 ");
		System.out.println("--------------------------------------");
		
		// 실제 데이터 출력
		if(list.size() == 0) //데이터가 존재하지 않으면
			System.out.println("데이터가 존재하지 않습니다.");
		else {
			for(MemberVO vo : list) {
				System.out.print("  " + vo.getId() + " / ");	
				System.out.print("  " + vo.getName() + " / ");	
				System.out.print("  " + vo.getAge() + " / ");	
				System.out.print("  " + vo.getTel() + " / ");	
				System.out.print("  " + vo.getGradeNo() + " / ");	
				System.out.print("  " + vo.getPhoto() + " / ");	
				System.out.print("  " + vo.getRegDate() + " / ");	
				System.out.println("  " + vo.getState());				
			}	// end of for
			System.out.println("-------------------------------");
		}	// end of if
	}	// end of list()

	// 회원 정보 출력
	public void view(MemberVO vo) throws Exception{
		System.out.println("+----[회원 정보]----+");
		
		// 실제 데이터 출력
		if(vo == null) //데이터가 존재하지 않으면
			{throw new Exception("데이터가 존재하지 않습니다.");}
		else {
			System.out.println(" ID :" + vo.getId());
			System.out.println(" 비밀번호 :" + vo.getPw());
			System.out.println(" 이름 :" + vo.getName());
			System.out.println(" 나이 :" + vo.getAge());
			System.out.println(" 전화번호 :" + vo.getTel());
			System.out.println(" 등급 :" + vo.getGradeNo());
			System.out.println(" 사진 :" + vo.getPhoto());
			System.out.println(" 가입날짜 :" + vo.getRegDate());
			System.out.println("-------------------------------");
		}
	}	// end of view
	
}
