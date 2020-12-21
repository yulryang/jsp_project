package com.team1.io.view;

import java.util.List;

import com.team1.qna.vo.QnAVO;

public class QnAPrint {
	public void list(List<QnAVO> list) {

		System.out.println("+----[게시판 리스트]----+");
		System.out.println("-------------------------------------");
		System.out.println("  글번호  /  제목  /  작성자  /  답변작성자  /  작성일  /  답변작성일  /  조회수");
		System.out.println("-------------------------------------");
		
		// 실제 데이터 출력
		if(list.size() == 0)	System.out.println("데이터가 존재하지 않습니다.");
		else {
			for(QnAVO vo : list) {
				System.out.print("  " + vo.getNo() + " / ");
				System.out.print("  " + vo.getTitle() + " / ");
				System.out.print("  " + vo.getWriter() + " / ");
				System.out.print("  " + vo.geta_Writer() + " / ");
				System.out.print("  " + vo.getWriteDate() + " / ");
				System.out.print("  " + vo.geta_WriteDate() + " / ");
				System.out.println("  " + vo.getHit());
			}	// end of for
			System.out.println("-------------------------------------");
		}	// end of if ~ else
	}	// end of list()
	
	public void view(QnAVO vo) throws Exception {

		System.out.println("+----[게시판 글보기]----+");
		if(vo == null) {	// 글번호가 맞지 않으면 null이 된다.
			throw new Exception("게시판 데이터를 가져올 수 없습니다.\n입력한 정보를 확인해주세요.");
		}
		System.out.println("-------------------------------------");
		System.out.println("  글번호  : " + vo.getNo());
		System.out.println("-------------------------------------");
		
		// 실제 데이터 출력
		System.out.println(" 제목 : " + vo.getTitle());
		System.out.println(" 내용 : " + vo.getQuestion());
		System.out.println(" 작성자 : " + vo.getWriter());
		System.out.println(" 작성일 : " + vo.getWriteDate());
		System.out.println(" 답변 내용 : " + vo.getAnswer());
		System.out.println(" 답변작성자 : " + vo.geta_Writer());
		System.out.println(" 답변작성일 : " + vo.geta_WriteDate());
		System.out.println(" 조회수 : " + vo.getHit());
		System.out.println("-------------------------------------");
	}	// end of view()
}
