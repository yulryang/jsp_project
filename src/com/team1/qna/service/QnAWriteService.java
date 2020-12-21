package com.team1.qna.service;

import com.team1.qna.dao.QnADAO;
import com.team1.qna.vo.QnAVO;

public class QnAWriteService {

	// 실행 메서드
	public int service(QnAVO vo) throws Exception {
//		System.out.println("QnAWriteService.service() " + vo);
		// DB에서 데이터를 넣어야 한다.
		// BoardDAO를 생성하고 호출한다.
		QnADAO dao = new QnADAO();
		// 데이터를 가져온다.
		int result = dao.write(vo);
		return result;
	}
}
