package com.team1.qna.controller;

import java.util.List;

import com.team1.qna.service.QnADeleteService;
import com.team1.qna.service.QnAListService;
import com.team1.qna.service.QnAUpdateService;
import com.team1.qna.service.QnAViewService;
import com.team1.qna.service.QnAWriteService;
import com.team1.qna.vo.QnAVO;
import com.team1.io.view.QnAPrint;
import com.team1.io.data.Input;

public class QnAController {

	public void control() {
		
		// 게시판 타이틀 출력
		System.out.println("******************");
		System.out.println("           질문과 답변");
		System.out.println("******************");
		System.out.println();
		
		// 아래 3가지가 무한 반복되어야 한다. 이전 메뉴를 선택하면 메인 메뉴로 간다.
		while(true) {
			// 1. 메뉴를 출력한다.
			System.out.println(">---------------------------------------<");
			System.out.println("1. 질문 목록 / 2. 질문 보기 / 3. 질문 작성");
			System.out.println("4. 질문 삭제 / 5. 답변작성 / 0. 이전 메뉴");
			System.out.println(">---------------------------------------<");
		
			// 2. 메뉴를 선택한다.
			String menu = Input.getString("메뉴 입력 ");
			
			try {
				QnAPrint out = null;
				QnAVO vo = null;
				int no = 0;
				QnAViewService QnAViewService = null;
				String pw = null;
				// 3. 메뉴를 처리한다.
				switch (menu) {
				case "1":
//					System.out.println("게시판 리스트 처리\n");
					// list 서비스 객체를 생성하고 호출
					QnAListService QnAListService = new QnAListService();
					List<QnAVO> list = QnAListService.service();
					// 출력한다. -> 출력하는 객체를 생성하고 호출한다.
					out = new QnAPrint();
					out.list(list);
					break;
				case "2":
					// 글번호 받기
					no = Input.getInt("보여줄 글 번호 선택 ");
					// DB에서 글번호에 맞는 데이터 가져오기
					// 보여줄 객체를 생성하고 호출한다.
					QnAViewService = new QnAViewService();
					vo = QnAViewService.service(no);
					// 가져온 BoardVO를 출력한다.
					out = new QnAPrint();
					out.view(vo);
					break;
				case "3":
					System.out.println("게시판 글쓰기 처리\n");
					// 데이터 입력 받기 - 제목, 내용, 작성자, 비밀번호
					vo = new QnAVO();
					setVO(vo);
					// controller -> service -> DAO
					// 생성하고 넘겨야한다.
					QnAWriteService QnAWriteService = new QnAWriteService();
					QnAWriteService.service(vo);
					break;
				case "4":
					System.out.println("게시판 글삭제 처리\n");
					// 삭제할 글 번호 입력
					no = Input.getInt("삭제할 글 번호");
					// 삭제할 글 비밀번호 입력
					pw = Input.getString("본인확인용 비밀번호 ");
					QnADeleteService QnADeleteService = new QnADeleteService();
					
					if(QnADeleteService.service(no, pw) == 0) {
						throw new Exception("글번호나 비밀번호를 확인하세요.");
					}
					break;
				case "5":
					System.out.println("답변 작성 처리\n");
					// 수정할 글 번호 입력
					no = Input.getInt("답변할 글 번호 선택 ");
					// 입력한 데이터 가져와서 표시하기 -> 수정할 데이터 확인
					// 글보기 처리를 생성해서 호출
					QnAViewService = new QnAViewService();
					vo = QnAViewService.service(no);
					
					out = new QnAPrint();
					out.view(vo);
					// 데이터 수정 (BoardVO) -> 수정항목 선택 반복처리
					// DB에 수정된 내용을 적용
					if (updateVO(vo)) {	// 수정한 결과가 true면 수정처리
						// 생성하고 호출
						QnAUpdateService boardUpdateService = new QnAUpdateService();
						if(boardUpdateService.service(vo) == 0) {
							throw new Exception("글번호나 비밀번호를 확인해주세요.");
						}
					}
					break;
				case "0":
					System.out.println();
					return;	// 돌아간다. -> MainController
				default:
					System.out.println("잘못된 메뉴를 선택하셨습니다. (0~5)\n");
					break;
				}	// end of switch
			} catch (Exception e) {
					// TODO: handle exception\
					System.out.println("+-------------------------+");
					System.out.println(" " + e.getMessage());
					System.out.println("+-------------------------+");
				}
		}	// while 문에 의한 무한반복의 끝
	}	// end of control()

	// 게시판 데이터를 입력받는 메서드
	private void setVO(QnAVO vo) {
		vo.setTitle(Input.getString("제목 "));
		vo.setQuestion(Input.getString("내용 "));
		vo.setWriter(Input.getString("작성자 "));
		vo.setPW(Input.getString("비밀번호 "));
	} // end of setVO()
	
	// 답변을 작성하는 메서드
		private boolean updateVO(QnAVO vo) {
			while(true) {
				// 수정항목 출력
				System.out.println("1. 답변 작성  9. 수정 취소  0. 수정 완료");
				// 수정항목 입력
				String item = Input.getString("수정할 항목 입력 ");	
				// 입력한 항목 처리 - switch
				switch (item) {
				case "1":
					vo.setAnswer(Input.getString("답변 "));
					break;
				case "9":
					return false;
				case "0":
					vo.setPW(Input.getString("비밀번호 "));
					return true;
				default:
					System.out.println("잘못된 항목을 입력하셨습니다.");
					break;
				}
			}
		}
}
