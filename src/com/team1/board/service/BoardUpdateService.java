package com.team1.board.service;

import com.team1.board.dao.BoardDAO;
import com.team1.board.vo.BoardVO;

public class BoardUpdateService {

	// 실행 메서드
	public int service(BoardVO vo) throws Exception{
		//System.out.println
		//("BoardUpdateService.service().vo" + vo);
		//DB에서 데이터를 넣어야 한다.
		// BoardDAO를 생성하고 호출한다.
		BoardDAO dao = new BoardDAO();
		// 데이터를 넣는다.
		int result = dao.update(vo);
		return result;
	}
	
}
