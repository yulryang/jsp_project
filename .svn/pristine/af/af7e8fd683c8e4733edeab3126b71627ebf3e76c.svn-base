package com.team1.qna.service;

import java.util.List;

import com.team1.qna.dao.QnADAO;
import com.team1.qna.vo.QnAVO;

public class QnAListService {

	// 실행 메서드
	public List<QnAVO> service() throws Exception {
//		System.out.println("BoardListService.service()");
		// DB에서 데이터를 꺼내와야 한다.
		// BoardDAO를 생성하고 호출한다.
		QnADAO boardDAO = new QnADAO();
		List<QnAVO> list = boardDAO.list();
		return list;
	}
}
