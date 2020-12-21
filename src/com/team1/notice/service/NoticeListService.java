package com.team1.notice.service;

import java.util.List;

import com.team1.notice.dao.NoticeDAO;
import com.team1.notice.vo.NoticeVO;

public class NoticeListService {

	// 실행 메서드
	public List<NoticeVO> service() throws Exception{
		NoticeDAO noticeDAO = new NoticeDAO();
		List<NoticeVO> list = noticeDAO.list();
		return list;
	}
	
}
