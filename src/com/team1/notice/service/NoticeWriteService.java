package com.team1.notice.service;

import com.team1.notice.dao.NoticeDAO;
import com.team1.notice.vo.NoticeVO;

public class NoticeWriteService {

	// 실행 메서드
	public int service(NoticeVO vo) throws Exception{
		//DB에서 데이터를 넣어야 한다.
		// BoardDAO를 생성하고 호출한다.
		NoticeDAO dao = new NoticeDAO();
		//데이터를 넣는다.
		int result = dao.write(vo);
		return result;
	}
	
}
