package com.team1.member.service;

import com.team1.member.dao.MemberDAO;
import com.team1.member.vo.MemberVO;

public class MemberViewService {
	
	// 실행 메소드
	public MemberVO service(String id) throws Exception { 
		// List : 리턴 타입, BoardVO : 클래스, service() : 메소드
//		System.out.println("MemberViewService.service()");
		//DB에서 데이터 꺼내오기
		// BoardDao를 생성하고 호출
		MemberDAO memberDAO = new MemberDAO();
		// 데이터 가져오기
		MemberVO vo = memberDAO.view(id);
		return vo;
	}

}
