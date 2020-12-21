package com.team1.notice.service;

import com.team1.notice.dao.NoticeDAO;

public class NoticeDeleteService {

	// 실행 메서드
	public int service(int no) throws Exception{
		//System.out.println("NoticeDeleteService.service()"+no+"/");
		//DB에서 데이터를 넣어야 한다.
		// BoardDAO를 생성하고 호출한다.
		NoticeDAO dao = new NoticeDAO();
		//데이터를 넣는다.
		int result = dao.delete(no);
		return result;
	}
	
}
