package com.team1.qna.service;

import com.team1.qna.dao.QnADAO;
import com.team1.qna.vo.QnAVO;

public class QnAViewService {

	// 실행 메서드
	public QnAVO service(int no) throws Exception {
//		System.out.println("QnAViewService.service()");
		// DB에서 데이터를 꺼내와야 한다.
		// BoardDAO를 생성하고 호출한다.
		QnADAO qnaDAO = new QnADAO();
		// 조회수 1 증가시킨다.
		qnaDAO.increaseHit(no);
		// 데이터를 가져온다.
		QnAVO vo = qnaDAO.view(no);
		return vo;
	}
}
