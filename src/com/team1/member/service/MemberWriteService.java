package com.team1.member.service;

import com.team1.member.dao.MemberDAO;
import com.team1.member.vo.MemberVO;

public class MemberWriteService {
	
	// 실행 메소드
	public int service(MemberVO vo) throws Exception { 
//		System.out.println("BoardWriteService.service().vo" + vo);
		//DB에 데이터 넣기
		// BoardDao를 생성하고 호출
		MemberDAO dao = new MemberDAO();
		// 데이터 넣기
		int result = dao.write(vo);
		return result;
	}

}
