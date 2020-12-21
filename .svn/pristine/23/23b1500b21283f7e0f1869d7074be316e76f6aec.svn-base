package com.team1.notice.service;

import com.team1.notice.dao.NoticeDAO;
import com.team1.notice.vo.NoticeVO;

public class NoticeViewService {

	// 실행 메서드
	public NoticeVO service(int no) throws Exception{
		//System.out.println("BoardViewService.service()");
		//DB에서 데이터를 꺼내와야 한다.
		// BoardDAO를 생성하고 호출한다.
		NoticeDAO noticeDAO = new NoticeDAO();
		
		
		//데이터를 가져온다.
		NoticeVO vo = noticeDAO.view(no);
		return vo;
	}
	
}
