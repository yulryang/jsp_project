package com.team1.board.service;

import com.team1.board.dao.BoardDAO;
import com.team1.board.vo.BoardVO;

public class BoardViewService {

	// 실행 메서드
	public BoardVO service(int no) throws Exception{
		//System.out.println("BoardViewService.service()");
		//DB에서 데이터를 꺼내와야 한다.
		// BoardDAO를 생성하고 호출한다.
		BoardDAO boardDAO = new BoardDAO();
		// 조회수 1 증가 시킨다.
		boardDAO.increaseHit(no);
		// 데이터를 가져온다.
		BoardVO vo = boardDAO.view(no);
		return vo;
	}
	
}

