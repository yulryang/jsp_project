package com.team1.main.controller;
//패키지 안에 모든 클래스를 사용하려면 패키지.*를 사용
//import java.util.Scanner;




import com.team1.board.controller.BoardController;
//import com.team1.board.controller.BoardController;
import com.team1.io.data.Input;
import com.team1.io.view.Print;
import com.team1.member.controller.MemberController;
import com.team1.member.service.LoginService;
import com.team1.member.service.MemberWriteService;
import com.team1.member.vo.LoginVO;
import com.team1.member.vo.MemberVO;
import com.team1.notice.controller.NoticeController;
import com.team1.qna.controller.QnAController;
//import com.team1.qna.vo.QnAVO;

public class Main {
	
	//로그인 처리를 하면 객체가 생성이 되어 넣어지는 변수
	//멤버 변수인 경우 기본값을 셋팅해 준디ㅏ. 참조형 변수의 기본값 : null
	public static LoginVO login;
	
	//
	// public 다른 곳에서도 접근 가능하도록 허용
	// static 고정, 클래스를 접곤하면 처음에 메모리로 자동 올리기
	// static으로 선언한 것을 다릉 클래스에서 사용할 때 클래스.변수명
	// final 넣어둔 값을 변경할 수 없음
	// ctrl shift enter 자동 들여쓰기
	//public static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 반복문을 시작하는 부분
		// while(조건) - 조건을 만족하는 동안에 계속 실행
		//while_loop: 
		while (true) {
			//사용자 정보 출력
			Print.printLoginInfo();
			System.out.println("   메인 메뉴    ");
			System.out.println(">---------------------------------------<");
			System.out.println("1.공지사항  2.게시판  3.질문답변 "
					+ ((login == null)?"4.회원가입 ":"4.회원정보 ")
					+ ((login==null)?"5.로그인 ":"5.로그아웃 ")+" 0.종료");
			System.out.println(">---------------------------------------<");
			//String menu = SCANNER.nextLine();
			String menu = Input.getString("메뉴를 선택해 주세요");

			switch (menu) {
			case "1":
				//System.out.println("***공지사항***");
				//System.out.println("공지사항 처리");
				NoticeController noticeController = new NoticeController();//메인메모리로 불러옴
				//메서드를 호출해서 처리할 수 있도록 해준다.
				noticeController.control();
				break;
			case "2":
//				System.out.println("***게시판***");
				//System.out.println("게시판 처리");
				//BoardController 객체 생성
				BoardController boardController = new BoardController();
				boardController.control();
				break;
			case "3":
				//System.out.println("***공지사항***");
				//System.out.println("공지사항 처리");
				QnAController QnAController = new QnAController();//메인메모리로 불러옴
				//메서드를 호출해서 처리할 수 있도록 해준다.
				QnAController.control();
				break;
			case "4":	// 회원 가입<->회원정보
//				System.out.println("***회원가입***");
//				System.out.println("회원가입 처리");

				if(login != null) {
            		// 회원 정보 클래스 연결
            		MemberController memberController
            		= new MemberController();
            		// 메소드 호출해서 회원 정보 처리
            		memberController.control();;
            	}            	
            	else {
            		MemberVO vo = new MemberVO();
            		joinVO(vo);
            		MemberWriteService memberWriteService
            		= new MemberWriteService();
            		try {
						memberWriteService.service(vo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		}
                break;
			case "5": // 로그인 <-> 로그아웃
//				System.out.println("***로그인***");
//				System.out.println("로그인 처리");
				if(login != null) {
                    // 로그아웃 처리
                    login = null;
                    System.out.println("*** 로그 아웃 ***");
                    break;
                }
                // 사용자에게 id와 pw 받기
                LoginVO loginVO = new LoginVO();
                loginVO.setId(Input.getString("아이디"));
                loginVO.setPw(Input.getString("비밀번호"));
                // 로그인 서비스 생성 및 호출
                LoginService loginService
                = new LoginService();
                try {
                    login = loginService.service(loginVO);
                    if(login == null) {
                        throw new Exception("아이디나 비밀번호를 확인하세요.");
                            // 사용자 오류 생성
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("+----------------------+");
                    System.out.println(" " + e.getMessage());
                    System.out.println("+----------------------+");
                }
                break;			
			case "0":
				System.out.println("*** 시스템 종료 ***");
				//System.exit(0);
				// 프로그램 종료 - 0 의도한 정상 종료
				//break while_loop;
				return; //void main() 아무것도 없는것을 돌려준다
			default:
				System.out.println("잘못된 메뉴를 선택하셨습니다.");
				
				break;
				
			}// end of switch
		} // 반복문의 끝
	}// end of main()

	// 질문과 답변, 답변 작성 메서드
//	private static boolean updateVO(QnAVO vo) {
//		while(true) {
//			// 수정항목 출력
//			System.out.println("1. 답변 작성  9. 수정 취소  0. 수정 완료");
//			// 수정항목 입력
//			String item = Input.getString("수정할 항목 입력 ");	
//			// 입력한 항목 처리 - switch
//			switch (item) {
//			case "1":
//				vo.setAnswer(Input.getString("답변 "));
//				break;
//			case "9":
//				return false;
//			case "0":
//				vo.setPW(Input.getString("비밀번호 "));
//				return true;
//			default:
//				System.out.println("잘못된 항목을 입력하셨습니다.");
//				break;
//			}
//		}
//	}   

	// 회원 가입 메소드
    private static void joinVO(MemberVO vo) {
    	vo.setId(Input.getString("ID"));
    	vo.setPw(Input.getString("비밀번호"));
    	vo.setName(Input.getString("이름"));
    	vo.setAge(Input.getInt("나이"));
    	vo.setTel(Input.getString("전화번호"));
    }	// end of joinVO()


}// end of class
