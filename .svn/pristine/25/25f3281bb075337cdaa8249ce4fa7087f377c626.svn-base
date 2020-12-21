package com.team1.member.controller;

import static com.team1.main.controller.Main.login;
import java.util.List;

import com.team1.io.data.Input;
import com.team1.io.view.Print;

import com.team1.member.service.MemberChangeService;
import com.team1.member.service.MemberDeleteService;
import com.team1.member.service.MemberLeaveService;
import com.team1.member.service.MemberListService;
import com.team1.member.service.MemberUpdateService;
import com.team1.member.service.MemberViewService;
import com.team1.member.vo.MemberVO;

/* 회원 정보 클래스 */
public class MemberController {
	
	// 게시판을 처리하는 처리문이 들어있는 메소드 사용
	public void control() {
		// static x-> main이 호출할 때만 사용하기 때문
		
		// 게시판 타이틀 출력
		System.out.println("*********************");
		System.out.println("               회원정보              " );
		System.out.println("*********************");
		
		// 게시판 처리 무한 반복
		while(true) {
			// 1. 메뉴 출력
			System.out.println(">---------------------------------------<");
			System.out.println(((login.getGradeNo() == 9)?"1.회원 목록 ":"1.내 정보 보기 ")
									+"2.내 정보 수정 3.비밀번호 변경 ");
			System.out.println(((login.getGradeNo() == 9)?"4.회원 강퇴 ":"4.회원 탈퇴 ")+ "0.이전메뉴");
			System.out.println(">---------------------------------------<");
			// 2. 메뉴 선택
			String menu = Input.getString("메뉴를 선택해 주세요");	
			
			try {	// 오류 발생시 리스트로 돌아가 다시 선택할 수 있도록 함
				Print out = null; // 두 번 이상 사용 -> 미리 선언
				MemberVO vo = null;
				MemberViewService memberViewService = new MemberViewService();
				// 3. 메뉴 처리
				switch (menu) { // main의 menu
				case "1":	//회원 목록<->내 정보 보기
					System.out.println();
//					System.out.println("게시판 리스트 처리 \n"); 
					if(login.getGradeNo() == 9) {
					// list 서비스 객체를 생성하고 호출
					MemberListService memberListService
					= new MemberListService();
					List<MemberVO> list 
					= memberListService.service(); 
						// 계속 쓰기 위해 변수로 저장
					// list 출력
					out = new Print();
					out.list(list);
					} else {
					//System.out.println("게시판 글보기 처리 \n");
					// DB에서 글번호에 맞는 데이터 가져오기
					// 보여줄 객체 생성 및 호출
					vo = memberViewService.service(login.getId());
					// 가져온 BoardVO 출력
					out = new Print();
					out.view(vo); 						
					}
					break;	// switch 문을 빠져나감				
				case "2":	// 정보 수정
					System.out.println();
//					System.out.println("게시판 글수정 처리 \n");
					// id 확인
					// 입력한 데이터 가져와서 표시-> 수정할 데이터 확인 <- 글보기
					memberViewService = new MemberViewService();
					vo = memberViewService.service(login.getId());
					// 데이터 수정(BoardVO) -> 수정항목 선택 반복 처리
					// DB에 수정된 내용 적용
					if(updateVO(vo)) {	// 수정한 결과가 true면 수정처리
						// 생성하고 호출
						MemberUpdateService memberUpdateService
						= new MemberUpdateService();
						if(memberUpdateService.service(vo) == 0)
							throw new Exception(
									"id나 비밀번호를 확인해주세요.");
					} else System.out.println("수정을 취소했습니다.");				
					break;
				case "3":	// 비밀 번호 변경
					System.out.println();
//					System.out.println("게시판 글수정 처리 \n");
					// 입력한 데이터 가져와서 표시-> 수정할 데이터 확인 <- 글보기
					memberViewService = new MemberViewService();
					vo = memberViewService.service(login.getId());
					// 데이터 수정(BoardVO) -> 수정항목 선택 반복 처리
					// DB에 수정된 내용 적용
					if(changeVO(vo)) {	// 수정한 결과가 true면 수정처리
						// 생성하고 호출
						MemberChangeService memberChangeService
						= new MemberChangeService();
						if(memberChangeService.service(vo) == 0)
							throw new Exception(
									"id나 비밀번호를 확인해주세요.");
					} else System.out.println("수정을 취소했습니다.");
					break;				
				case "4":	// 회원 강퇴 <-> 회원 탈퇴
					System.out.println();
//					System.out.println("게시판 글삭제 처리 \n");
					if(login.getGradeNo()==9) {
						String id = Input.getString("강퇴할 ID 입력");
						MemberLeaveService memberLeaveService
						= new MemberLeaveService();
						if(memberLeaveService.service(id) == 0)
							throw new Exception("id를 확인해주세요.");
					break;}
					else {
					// id 확인
					memberViewService = new MemberViewService();
					vo = memberViewService.service(login.getId());
					// 삭제하고 생성
					if(deleteVO(vo)) {
					MemberDeleteService memberDeleteService
					= new MemberDeleteService();
					if(memberDeleteService.service(vo) == 0)
						throw new Exception("id나 비밀번호를 확인해주세요.");
					login = null;}
	                    return;}
				case "0":
					return;	
					// control 메소드를 빠져나감-> MainController으로 돌아감
	
				default:
					System.out.println("잘못된 메뉴 선택(0~4번 중 선택) \n");
					break;
				}	// end of switch
			} catch (Exception e) {
				System.out.println("+----------------------+");
				System.out.println(" " + e.getMessage());
				System.out.println("+----------------------+");
			}
		}	// end of while	
	}	// end of control()

	// 회원 정보를 수정하는 메소드
	private boolean updateVO(MemberVO vo) {	// boolean 타입 리턴 : t/f
		while(true) {
			// 수정 항목 출력
			System.out.println("*** 수정 항목 선택 ***");
			System.out.println("1.이름 2.전화번호 9.수정 취소 0.수정완료");
			// 수정항목 입력
			String item = Input.getString("수정할 항목 입력");
			// 입력한 항목 처리 - switch
			switch (item) {
			case "1":
				vo.setName(Input.getString("이름"));
				break;
			case "2":
				vo.setTel(Input.getString("내용"));
				break;
			case "9":
				return false;
			case "0":
				vo.setPw(Input.getString("비밀번호 입력"));
				return true;	

			default:
				System.out.println("잘못된 항목을 입력했습니다.");
				break;
			}
		}	// end of while
	}

	// 비밀번호를 수정하는 메소드
	private boolean changeVO(MemberVO vo) {	// boolean 타입 리턴 : t/f
				vo.setPw(Input.getString("기존 비밀번호"));
				vo.setNewPw(Input.getString("수정할 비밀번호"));
				return true;
	}

	// 회원 탈퇴 메소드
	private boolean deleteVO(MemberVO vo) {
		vo.setPw(Input.getString("비밀번호 확인"));
		return true;
	}
}
