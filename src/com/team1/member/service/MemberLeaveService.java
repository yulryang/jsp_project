package com.team1.member.service;

import com.team1.member.dao.MemberDAO;

public class MemberLeaveService {
	
	// 실행 메소드
	public int service(String id) throws Exception { 
/*		System.out.println("BoardDeleteService.service().no/pw" + 
				no + "/" + pw); */
		//DB에 데이터 넣기
		// BoardDao를 생성하고 호출
		MemberDAO dao = new MemberDAO();
		// 데이터 넣기
		int result = dao.leave(id);
		return result;
	}

}
