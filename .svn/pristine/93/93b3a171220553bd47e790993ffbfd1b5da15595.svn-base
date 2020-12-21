package com.team1.member.service;

import java.util.List;

import com.team1.member.dao.MemberDAO;
import com.team1.member.vo.MemberVO;

public class MemberListService {
	
	// 실행 메소드
	public List<MemberVO> service() throws Exception { 
		// List : 리턴 타입, BoardVO : 클래스, service() : 메소드
		// System.out.println("BoardListService.service()");
		//DB에서 데이터 꺼내오기
		// BoardDao를 생성하고 호출
		MemberDAO memberDAO = new MemberDAO();
		List<MemberVO> list = memberDAO.list();
		return list;
	}

}
