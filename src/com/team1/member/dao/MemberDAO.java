package com.team1.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.member.vo.LoginVO;
import com.team1.member.vo.MemberVO;
import com.team1.util.db.DBInfo;

/* 회원관리 DB 처리 클래스 */
public class MemberDAO {
	
	// 로그인 처리
	public LoginVO login(LoginVO memberVO) throws Exception{

//		System.out.println("MemberDAO.view().memberVO:" + memberVO);
		LoginVO vo = null;
			
		// 필요한 객체 선언 : select - con, pstmt, rs
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		ResultSet rs = null; // DB에 저장
			
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 조회 sql문			
			String sql = "select m.id, m.name, m.gradeNo, g.gradeName "
					+ "from member m, grade g "
					+ "where (id = ? and pw = ?) "	// 일반 조건
					+ "and (m.gradeNo = g.gradeNo)";	// 조인 조건
			// 4. 실행 객체 + 데이터 셋팅(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPw());
			// 5. 실행 - select-> executeQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> vo에 데이터 담기 (데이터 한 개-> 반복문 사용x)
			if(rs != null && rs.next()) {
					// vo 객체 생성을 하며 데이터를 집어넣음					
				vo = new LoginVO(
						rs.getString("id"),
						null,	// pw는 가져오지 않음
						rs.getString("name"),
						rs.getInt("gradeNo"),
						rs.getString("gradeName"));
			}	// end of if			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("로그인 처리 중 오류가 발생했습니다.");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 데이터가 잘 들어갔는지 확인
//		System.out.println("MemberDAO.login().vo: " + vo);
				
		return vo;
	}

	// 회원 목록 보기
	public List<MemberVO> list() throws Exception{

		// System.out.println("BoardDao.list()");
		List<MemberVO> list = null;
		// List : interface -> 클래스 호출해서 처리 : ArrayList
		
		// 필요한 객체 선언 : select - con, pstmt, rs
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		ResultSet rs = null; // DB에 저장
		
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 조회 sql문
			String sql = "select id, name, age, "
					+ "tel, gradeNo, photo, "
					+ "to_char(regDate, 'yyyy.mm.dd') regDate, "
			// to_char: 출력 형식 바꾸기	컬럼					별칭
					+ "state " 
					+ "from member order by regDate desc";
			// 4. 실행 객체
			pstmt = con.prepareStatement(sql);
			// 5. 실행 - select-> executeQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> List에 데이터 담기
			if(rs != null) {
				while(rs.next()) {	// 데이터가 여러개이므로 반복문
					// list 객체가 한번만 생성이 되도록 처리 -> list == null 조건
					if(list == null) list= new ArrayList<>();
					// 데이터의 개수만큼 BoardVo 객체가 생성이 되어야함
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setAge(rs.getInt("age"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setPhoto(rs.getString("photo"));
					vo.setRegDate(rs.getString("regDate")); // 별칭 사용
					vo.setState(rs.getString("state"));
					// 저장된 데이터 객체 list에 담기
					list.add(vo);
				}	// end of while
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 목록을 처리하는 중 오류가 발생하였습니다.");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 데이터가 잘 들어갔는지 확인
		//System.out.println("MemberDAO.list().list: " + list);
//		System.out.println(list);
		
		return list;
	}

	// 개인 정보 보기
	public MemberVO view(String id) throws Exception{

//		System.out.println("BoardDao.view()");
		MemberVO vo = null;
			
		// 필요한 객체 선언 : select - con, pstmt, rs
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		ResultSet rs = null; // DB에 저장
		
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 조회 sql문
			String sql = "select id, pw, name, age, "
					+ "tel, gradeNo, photo, "
					+ "to_char(regDate, 'yyyy.mm.dd') regDate "
			// to_char: 출력 형식 바꾸기	컬럼					별칭
					+ "from member where id = ?";
			// 4. 실행 객체 + 대체문자 처리(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 5. 실행 - select-> executeQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> vo에 데이터 담기(데이터 한개-> 반복문 사용x)
			if(rs != null && rs.next()) {	
					
					// vo 객체 생성
					vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setPw(rs.getString("pw"));
					vo.setName(rs.getString("name"));
					vo.setAge(rs.getInt("age"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setPhoto(rs.getString("photo"));
					vo.setRegDate(rs.getString("regDate")); // 별칭 사용
			}	// end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 정보를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 데이터가 잘 들어갔는지 확인
//		System.out.println("MeberVo.view().vo: " + vo);
		
		return vo;
	}

	// 회원 가입
	public int write(MemberVO vo) throws Exception{

		// System.out.println("BoardDao.list()");
		int result = 0;
			
		// 필요한 객체 선언 : insert - con, pstmt
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 조회 sql문
			String sql = "insert into member("
					+ "id, pw, name, age, tel) "
					+ "values(?, ?, ?, ? ,?)";
			// 4. 실행 객체 + 대체문자 처리(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getTel());
			// 5. 실행 - insert-> executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(데이터 한개-> 반복문 사용x)
			if(result == 0) 	// 쓰기가 정상적으로 일어나지 않은 경우
				throw new Exception();
			System.out.println("가입 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 정보를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		// 데이터가 잘 들어갔는지 확인
		//System.out.println("MeberVo.write().vo: " + vo);
		
		return result;
	}

	// 정보 수정
	public int update(MemberVO vo) throws Exception{

//		System.out.println(vo);
		int result = 0;
			
		// 필요한 객체 선언 : update - con, pstmt
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 수정 sql문
			String sql = "update member set "
					+ "name = ?, tel = ? "
					+ "where id = ? and pw = ?";
			// 4. 실행 객체 + 대체문자 처리(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getPw());
			// 5. 실행 - update-> executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(데이터 한개-> 반복문 사용x)
			System.out.println("수정 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("정보를 수정하는 중 오류가 발생하였습니다."
					+ "(정보를 확인 후 다시 실행해주세요)");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		// 데이터가 잘 들어갔는지 확인
//		System.out.println("MeberVo.write().vo: " + vo);
		
		return result;
	}

	// 비밀 번호 변경
	public int change(MemberVO vo) throws Exception{

//		System.out.println(vo);
		int result = 0;
			
		// 필요한 객체 선언 : update - con, pstmt
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 수정 sql문
			String sql = "update member set pw = ? "
					+ "where id = ? and pw = ?";
			// 4. 실행 객체 + 대체문자 처리(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getNewPw());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			// 5. 실행 - update-> executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(데이터 한개-> 반복문 사용x)
			System.out.println("변경 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("비밀번호를 변경하는 중 오류가 발생하였습니다."
					+ "(정보를 확인 후 다시 실행해주세요)");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		// 데이터가 잘 들어갔는지 확인
//		System.out.println("MeberVo.change().vo: " + vo);
		
		return result;
	}

	// 회원 탈퇴 하기
	public int delete(MemberVO vo) 
			throws Exception{

		int result = 0;
			
		// 필요한 객체 선언 : delete - con, pstmt
		Connection con = null;	// 연결
		PreparedStatement pstmt = null;	// 실행
		
		// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
		try {
			// 1. 드라이버 확인  + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. 수정 sql문
			String sql = "delete from member "
					+ "where id = ? and pw = ?";
			// 4. 실행 객체 + 대체문자 처리(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			// 5. 실행 - delete-> executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(데이터 한개-> 반복문 사용x)
			System.out.println("탈퇴 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("탈퇴 중 오류가 발생하였습니다.");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		// 데이터가 잘 들어갔는지 확인
		//System.out.println("MeberVo.change().vo: " + vo);
		
		return result;
	}

	// 회원 강퇴 하기
			public int leave(String id) 
					throws Exception{
				
				int result = 0;
					
				// 필요한 객체 선언 : delete - con, pstmt
				Connection con = null;	// 연결
				PreparedStatement pstmt = null;	// 실행
				
				// DB에서 데이터 꺼내오기 - try catch 문으로 오류 확인
				try {
					// 1. 드라이버 확인  + 2. 연결 객체
					con = DBInfo.getConnection();
					// 3. 수정 sql문
					String sql = "delete from member "
							+ "where id = ?";
					// 4. 실행 객체 + 대체문자 처리(?)
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					// 5. 실행 - delete-> executeUpdate()
					result = pstmt.executeUpdate();
					// 6. 표시 -> vo에 데이터 담기(데이터 한개-> 반복문 사용x)
					System.out.println("강퇴 완료");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new Exception("탈퇴 중 오류가 발생하였습니다.");
				} finally {
					// 7. 닫기
					DBInfo.close(con, pstmt);
				}
				
				// 데이터가 잘 들어갔는지 확인
				//System.out.println("MeberVo.change().vo: " + vo);
				
				return result;
			}

}
