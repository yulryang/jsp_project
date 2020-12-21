package com.team1.member.service;

import com.team1.member.dao.MemberDAO;
import com.team1.member.vo.LoginVO;

/* 로그인 처리 클래스 */
public class LoginService {
	
	// 실행 메소드
	public LoginVO service(LoginVO loginVO) throws Exception { 
		// LoginVO : 리턴 타입  service() : 메소드
//		System.out.println("LoginService.service()");
		//DB에서 데이터 꺼내오기
		// BoardDao를 생성하고 호출
		MemberDAO dao = new MemberDAO();
		// 데이터 가져오기
		LoginVO vo = dao.login(loginVO);
		return vo;
	}

}
