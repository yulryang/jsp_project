package com.team1.board.service;

import java.util.List;

import com.team1.board.dao.BoardDAO;
import com.team1.board.vo.BoardVO;

public class BoardListService {

	// 실행 메서드
	public List<BoardVO> service() throws Exception{
		//System.out.println("BoardListService.service()");
		//DB에서 데이터를 꺼내와야 한다.
		// BoardDAO를 생성하고 호출한다.
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> list = boardDAO.list();
		return list;
	}
	
}
