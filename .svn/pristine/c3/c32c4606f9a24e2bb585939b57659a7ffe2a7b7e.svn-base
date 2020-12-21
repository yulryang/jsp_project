package com.team1.notice.controller;

import java.util.List;

import com.team1.io.data.Input;
import com.team1.io.view.NoticePrint;
import com.team1.notice.service.NoticeListService;
import com.team1.notice.service.NoticeViewService;
import com.team1.notice.service.NoticeWriteService;
import com.team1.notice.service.NoticeDeleteService;
import com.team1.notice.service.NoticeUpdateService;
import com.team1.notice.vo.NoticeVO;

//import com.webjjang.member.vo.LoginVO;
import static com.team1.main.controller.Main.login;

public class NoticeController {
	
	
	

	public void control() {
		
		System.out.println("*********************");
		System.out.println("               공지사항              " );
		System.out.println("*********************");

		while(true) {
			System.out.println(">---------------------------------------<");
			System.out.println("1.공지사항 목록  2.공지사항 보기  3.공지사항 쓰기");
			System.out.println("4.공지사항 수정  5.공지사항 삭제  0.이전메뉴");
			System.out.println(">---------------------------------------<");
			String menu =Input.getString("메뉴를 선택해 주세요");
			try {
			NoticePrint out = new NoticePrint();
			int no;
			//NoticeUpdateService noticeUpdateService = new NoticeUpdateService();
			NoticeViewService noticeViewService = null;
			NoticeVO vo = null;
			switch (menu) {
			case "1":
				
				//System.out.println("공지사항 리스트");
				NoticeListService noticeListService = new NoticeListService();
				List<NoticeVO> list = noticeListService.service();
				
				out.list(list);
				break;
			case "2":
				//System.out.println("공지사항 보기");
				
				no = Integer.parseInt(Input.getString("글 번호 선택 "));
				noticeViewService = new NoticeViewService();
				vo = noticeViewService.service(no);
				// System.out.print(vo);
				//out = new NoticePrint();
				out.view(vo);
				break;
			case "3":
				//System.out.println("공지사항 글쓰기");
				if (login==null) {
					System.out.println("권한이 없습니다. 로그인을 해주세요.");
				break;}
				if(login.getGradeNo()!=9) {
					System.out.println("권한이 없습니다. 회원등급을 확인해 주세요.");
				break;}
				
				vo = new NoticeVO();
				setVO(vo);
				// controller ->service ->dao
				NoticeWriteService noticeWriteService = new NoticeWriteService();
				noticeWriteService.service(vo);
				
				
				break;
			case "4":
				//System.out.println("공지사항 수정");
				
				if (login==null) {
					System.out.println("권한이 없습니다. 로그인을 해주세요.");
				break;}
				if(login.getGradeNo()!=9) {
					System.out.println("권한이 없습니다. 회원등급을 확인해 주세요.");
				break;}
				no = Input.getInt("수정할 글번호 선택");
				noticeViewService = new NoticeViewService();
				vo = noticeViewService.service(no);
				//out = new BoardPrint();
				out.view(vo);
				if(updateVO(vo)) {//수정한 결과가 true면 수정처리
					//생성하고 호출
				NoticeUpdateService noticeUpdateService = new NoticeUpdateService();
				if(noticeUpdateService.service(vo)==0)
						throw new Exception("글번호를 확인해 주세요.");
				}
				else System.out.println("수정을 취소하셨습니다.");
				break;
				
			case "5":
				//System.out.println("공지사항 삭제");
				if (login==null) {
					System.out.println("권한이 없습니다. 로그인을 해주세요.");
				break;}
				if(login.getGradeNo()!=9) {
					System.out.println("권한이 없습니다. 회원등급을 확인해 주세요.");
				break;}
				no = Input.getInt("삭제할 글번호");
				
				NoticeDeleteService noticeDeleteService = new NoticeDeleteService();
				if(noticeDeleteService.service(no)==0)
					throw new Exception("글 번호를 확인하세요.");
				
				
				break;
			case "0":
				return;//main으로 돌아간다
		
			default:
				System.out.println("잘못된 메뉴를 선택하셨습니다.(0-5)");
				break;
			}
		}
			catch (Exception e) {
				System.out.println("+-----------------------+");
				System.out.println(" " + e.getMessage());
				System.out.println("+-----------------------+");
			}

			
		}
}
		private void setVO(NoticeVO vo) {
			vo.setTitle(Input.getString("제목"));
			vo.setContent(Input.getString("내용"));
			vo.setStartDate(Input.getString("공지시작일"));
			vo.setEndDate(Input.getString("공지종료일"));

		}// end of setVO()
			// 게시판을 수정하는 메서드

		private boolean updateVO(NoticeVO vo) {
			while (true) {
				// 수정 항목 출력
				System.out.println("1. 제목   2. 내용    3. 공지시작일    4.공지종료일   9. 수정취소  0.수정완료");
				// 수정 항목 입력
				String item = Input.getString("수정할 항목 입력");
				// 입력한 항목 처리 - switch
				switch (item) {
				case "1":
					vo.setTitle(Input.getString("제목"));
					
					break;
				case "2":
					vo.setContent(Input.getString("내용"));
					break;
				case "3":
					vo.setStartDate(Input.getString("공지시작일"));
					
					break;
				case "4":
					vo.setEndDate(Input.getString("공지종료일"));
					
					break;
				case "9":
					return false;
				case "0":
					
					return true;
				default: 
					System.out.println("잘못된 항목을 입력하셨습니다.");
					break;
				
				}
			}
	}
	
}// end of class
