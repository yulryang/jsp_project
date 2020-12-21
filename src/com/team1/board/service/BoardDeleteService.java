package com.team1.board.service;

import com.team1.board.dao.BoardDAO;
//import com.team1.board.vo.BoardVO;


public class BoardDeleteService {

	// 실행 메서드
	public int service(int no, String pw) throws Exception{
		//System.out.println
		//("BoardDeleteService.service().no/pw" + no + "/" + pw);
		//DB에서 데이터를 넣어야 한다.
		// BoardDAO를 생성하고 호출한다.
		BoardDAO dao = new BoardDAO();
		// 데이터를 넣는다.
		int result = dao.delete(no, pw);
		return result;
	}
	
}
