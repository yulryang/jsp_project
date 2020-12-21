package com.team1.io.view;
/*
 * 게시판 데이터를 화면에 출력하는 클래스
 * 리스트 / 보기
 */

import java.util.List;

import com.team1.notice.vo.NoticeVO;

public class NoticePrint {

	public void list(List<NoticeVO> list) {
		System.out.println("+----[공지사항 리스트]----+");
		System.out.println("----------------------------------------------------------");
		System.out.println(" 글번호 /     제목            /    작성일     /    공지시작일    /    공지종료일        ");
		System.out.println("----------------------------------------------------------");
		// 실제 데이터 출력
		if (list.size() == 0)// 데이터 존재하지 않을 때
			System.out.println("데이터가 존재하지 않습니다.");
		else {
			for (NoticeVO vo : list) {
				System.out.print(" " + vo.getNo() + " / ");
				System.out.print(" " + vo.getTitle() + " / ");
				System.out.print(" " + vo.getWriteDate() + " / ");
				System.out.print(" " + vo.getStartDate() + " / ");
				System.out.println(" " + vo.getEndDate() + " / ");
				

			} // end of for
			System.out.println("----------------------------------------------------------");
		} // end of if ~ else

	}// end of list()

	public void view(NoticeVO vo) throws Exception {
		System.out.println("+----[공지사항 글보기]----+");
		if (vo == null) { // 글번호가 맞지 않으면 null이 된다.
			throw new Exception("공지사항 데이터를 가져올 수 없습니다.\n" + "입력한 정보를 확인해 주세요.");
		}
		System.out.println("------------------------------------------");
		System.out.println(" 글번호  " + vo.getNo());
		System.out.println("------------------------------------------");
		// 실제 데이터 출력
		System.out.println(" 제목 : " + vo.getTitle());
		System.out.println(" 내용 : " + vo.getContent());
		System.out.println(" 작성일 : " + vo.getWriteDate());
		System.out.println(" 공지시작일 : " + vo.getStartDate());
		System.out.println(" 공지종료일 : " + vo.getEndDate());
		
		

		System.out.println("------------------------------------------");

	}// end of view()

}
//end of class
