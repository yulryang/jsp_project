package com.team1.notice.service;

import com.team1.notice.dao.NoticeDAO;
import com.team1.notice.vo.NoticeVO;

public class NoticeUpdateService {

	// 실행 메서드
	public int service(NoticeVO vo) throws Exception{
		NoticeDAO dao = new NoticeDAO();
		//데이터를 넣는다.
		int result = dao.update(vo);
		return result;
	}
	
}
