package com.team1.io.view;

import java.util.List;

import com.team1.board.vo.BoardVO;


//게시판 데이터를 화면에 출력하는 클래스
//리스트/보기
public class BoardPrint {

	public void list(List<BoardVO> list) {
		System.out.println("+----[게시판 리스트]----+");
		System.out.println
		("--------------------------------------------");
		System.out.println
		("  글번호  /     제목       / 작성자   / 작성일   / 조회수");
		System.out.println
		("--------------------------------------------");
		// 실제 데이터 출력
		if(list.size() == 0)
			System.out.println("    데이터가 존재하지 않습니다.");
		else {
			for(BoardVO vo : list) {
				System.out.print(" " + vo.getNo() + " / ");
				System.out.print(" " + vo.getTitle() + " / ");
				System.out.print(" " + vo.getWriter() + " / ");
				System.out.print(" " + vo.getWriteDate() + " / ");
				System.out.println(" " + vo.getHit());
			} // end of for
			System.out.println
			("--------------------------------------------");
		} // end of if ~ else
	}// end of list()

	public void view(BoardVO vo) throws Exception{
		System.out.println("+----[게시판 글보기]----+");
		if(vo == null) { // 글번호가 맞지 않으면 null이 된다.
			throw new Exception("게시판 데이터를 가져 올 수 없습니다.\n"
					+ "입력한 정보를 확인해 주세요.");
		}
		System.out.println
		("--------------------------------------------");
		System.out.println
		("  글번호  : " + vo.getNo());
		System.out.println
		("--------------------------------------------");
		// 실제 데이터 출력
		System.out.println(" 제목 : " + vo.getTitle());
		System.out.println(" 내용 : " + vo.getContent());
		System.out.println(" 작성자 : " + vo.getWriter());
		System.out.println(" 작성일 : " + vo.getWriteDate());
		System.out.println(" 조회수 : " + vo.getHit());
		System.out.println
		("--------------------------------------------");
	}}// end of view()

	