package com.team1.qna.service;

import com.team1.qna.dao.QnADAO;

public class QnADeleteService {

	// 실행 메서드
	public int service(int no, String pw) throws Exception {
//		System.out.println("QnADeleteService.service().no/pw " + no + " / " + pw);
		// DB에서 데이터를 넣어야 한다.
		// BoardDAO를 생성하고 호출한다.
		QnADAO dao = new QnADAO();
		// 데이터를 가져온다.
		int result = dao.delete(no, pw);
		return result;
	}
}
