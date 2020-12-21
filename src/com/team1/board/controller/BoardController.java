package com.team1.board.controller;

import java.util.List;

import com.team1.board.service.BoardDeleteService;
import com.team1.board.service.BoardListService;
import com.team1.board.service.BoardUpdateService;
import com.team1.board.service.BoardViewService;
import com.team1.board.service.BoardWriteService;
import com.team1.board.vo.BoardVO;
import com.team1.io.data.Input;
import com.team1.io.view.BoardPrint;

public class BoardController {

	// 게시판의 처리가 들어 있는 메서드
	public void control() {
		
		// 게시판 제목 출력
		System.out.println();
		System.out.println("**************");
		System.out.println("   게 시 판");
		System.out.println("**************");
		System.out.println();
		
		// 게시판 처리 무한 반복
		while(true) {
			
			// 메뉴 출력
			System.out.println(">---------------------------------------<");
			System.out.println("1.게시글 목록 2.게시글 보기 3.새 글 쓰기");
			System.out.println("4.게시글 수정 5.게시글 삭제 0.이전메뉴");
			System.out.println(">---------------------------------------<");
			// 메뉴 입력
			String menu = Input.getString("메뉴를 선택해 주세요");
			
			try {
				BoardPrint out = null;
				BoardVO vo = null;
				int no = 0;
				BoardViewService boardViewService = null;
				// 메뉴 처리
				switch (menu) {
				case "1": // 리스트
					//System.out.println("게시판 리스트 처리\n");
					// list 서비스 객체를 생성하고 호출
					BoardListService boardListService
					= new BoardListService();
					List<BoardVO> list = 
							boardListService.service();
					// 출력한다. -> 출력하는 객체를 생성하고 호출한다.
					out = new BoardPrint();
					out.list(list);
					break;
				case "2": // 보기
					//System.out.println("게시판 보기 처리\n");
					// 글번호 받기
					no = Input.getInt("보여줄 글번호 선택");
					// DB에서 글번호에 맞는 데이터 가져오기
					// 보여줄 객체를 생성하고 호출한다.
					boardViewService
					= new BoardViewService();
					vo = boardViewService.service(no);
					// 가져온 BoardVO를 출력한다.
					out = new BoardPrint();
					out.view(vo);
					break;
				case "3": // 쓰기
					//System.out.println("게시판 쓰기 처리\n");
					// 데이터 입력 받기 - 제목, 내용, 작성자, 비밀번호
					vo = new BoardVO();
					setVO(vo);
					// Controller -> Service -> DAO
					// 생성하고 호출
					BoardWriteService boardWriteService
					= new BoardWriteService();
					boardWriteService.service(vo);
					break;
				case "4": // 수정
					//System.out.println("게시판 수정 처리\n");
					// 수정할 글번호 입력
					no = Input.getInt("수정할 글번호 선택");
					// 입력한 데이터 가져와서 표시하기 -> 수정할 데이터 확인
					// 글보기 처리를 생성해서 호출
					boardViewService = new BoardViewService(); 
					vo = boardViewService.service(no);
					out = new BoardPrint();
					out.view(vo);
					// 데이터 수정 (BoardVO) -> 수정항목 선택 반복처리
					// DB에 수정된 내용 적용
					if(updateVO(vo)) {// 수정한 결과가 true면 수정처리
						// 생성하고 호출
						BoardUpdateService boardUpdateService
						= new BoardUpdateService();
						if(boardUpdateService.service(vo)==0)
							throw new Exception
							("글번호나 비밀번호를 확인해 주세요.");
					}
					else System.out.println("수정을 취소하셨습니다.");
					break;
				case "5": // 삭제
					//System.out.println("게시판 삭제 처리\n");
					// 삭제할 글번호 입력
					no = Input.getInt("삭제할 글번호");
					// 삭제할 비밀번호 입력
					String pw = Input.getString("본인확인용 비밀번호");
					BoardDeleteService boardDeleteService
					= new BoardDeleteService();
					if(boardDeleteService.service(no, pw) == 0)
						throw new Exception("글번호나 비밀번호를 확인하세요.");
					break;
				case "0": // 이전 메뉴
					// 메서드()를 빠져 나간다.->메인으로 돌아간다.
					return;
	
				default:
					System.out.println("잘못된 메뉴를 선택하셨습니다.");
					System.out.println
					("1~5, 0 중에서 하나를 선택하세요.\n");
					break;
				}// end of switch
			} catch (Exception e) {
				// TODO: handle exception\
				System.out.println("+-------------------------+");
				System.out.println(" " + e.getMessage());
				System.out.println("+-------------------------+");
			}
			
		}// end of while(true)
		
	}// end of control()
	
	// 게시판 데이터를 입력 받는 메서드
	private void setVO(BoardVO vo) {
		vo.setTitle(Input.getString("제목"));
		vo.setContent(Input.getString("내용"));
		vo.setWriter(Input.getString("작성자"));
		vo.setPw(Input.getString("비밀번호"));
	}// end of setVO()
	
	// 게시판 데이터를 수정하는 메서드
	private boolean updateVO(BoardVO vo) {
		while(true) {
			// 수정 항목 출력
			System.out.println
			("1.제목  2.내용  3.작성자  9.수정 취소  0.수정완료");
			// 수정 항목 입력
			String item = Input.getString("수정 항목 입력");
			// 입력한 항목 처리 - switch
			switch (item) {
			case "1":
				vo.setTitle(Input.getString("제목"));
				break;
			case "2":
				vo.setContent(Input.getString("내용"));
				break;
			case "3":
				vo.setWriter(Input.getString("작성자"));
				break;
			case "9":
				return false;
			case "0":
				vo.setPw(Input.getString("본인 확인용 비밀번호"));
				return true;
				
			default:
				System.out.println("잘못된 항목을 입력하셨습니다.");
				break;
			}
		}
	}
	
}//end of class
