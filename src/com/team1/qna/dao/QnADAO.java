package com.team1.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.qna.vo.QnAVO;
import com.team1.util.db.DBInfo;

public class QnADAO {
	// 게시판 리스트
		public List<QnAVO> list() throws Exception {
			List<QnAVO> list = new ArrayList<QnAVO>();
			
			// 필요한 객체 선언 - select : conn, pstmt, rs
			Connection conn = null;			// 연결
			PreparedStatement pstmt = null;	// 실행
			ResultSet rs = null;			// DB 저장
			
			// DB에서 데이터 꺼내오기
			try {
				// 1. 드라이버 확인 + 2. 연결 객체
				conn = DBInfo.getConnection();
				// 3. sql
				String sql = "select no, title, writer, a_writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, to_char(a_writeDate, 'yyyy.mm.dd') a_writeDate, hit from qnaboard order by no desc";
				// 4. 실행 객체
				pstmt = conn.prepareStatement(sql);
				// 5. 실행 - select : executeQuery()
				rs = pstmt.executeQuery();
				// 6. 표시 - list에 데이터 담기
				if (rs != null) {
					while(rs.next()) {
						// list 객체가 한번만 생성이 되도록 처리
						if(list == null)	list = new ArrayList<QnAVO>();
						// 데이터의 갯수만큼 QnAVO 객체가 생성이 되어야 한다.
						QnAVO vo = new QnAVO();
						vo.setNo(rs.getInt("no"));
						vo.setTitle (rs.getString("title"));
						vo.setWriter(rs.getString("writer"));
						vo.seta_Writer(rs.getString("a_writer"));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.seta_WriteDate(rs.getString("a_writeDate"));
						vo.setHit(rs.getInt("hit"));
						
						// 저장된 데이터 객체를 list에 담는다.
						list.add(vo);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("게시판 리스트를 처리하는 중 오류가 발생하였습니다.");
			} finally {
				// 7. 닫기
				DBInfo.close(conn, pstmt, rs);
			}
			// 데이터가 잘 들어가 있는지 확인한다.
//			System.out.println("QnADAO.list().list:" + list);
			
			return list;
		}

		// 게시판 글보기
		public QnAVO view(int no) throws Exception {

//			System.out.println("QnADAO.view() " + no);
			QnAVO vo = null;
			
			// 필요한 객체 선언 - select : conn, pstmt, rs
			Connection conn = null;			// 연결
			PreparedStatement pstmt = null;	// 실행
			ResultSet rs = null;			// DB 저장
			
			// DB에서 데이터 꺼내오기
			try {
				// 1. 드라이버 확인 + 2. 연결 객체
				conn = DBInfo.getConnection();
				// 3. sql
				String sql = "select no, title, question, answer, writer, a_writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, to_char(a_writeDate, 'yyyy.mm.dd') a_writeDate, hit from qnaboard where no=?";
				// 4. 실행 객체 + 데이터셋팅(?)
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				// 5. 실행 - select : executeQuery()
				rs = pstmt.executeQuery();
				// 6. 표시 - vo에 데이터 담기 (한개-반복문 사용하지 않는다.)
				if (rs != null) {
					if(rs.next()) {
						// vo 객체 생성
						vo = new QnAVO();
						vo.setNo(rs.getInt("no"));
						vo.setTitle (rs.getString("title"));
						vo.setQuestion (rs.getString("question"));
						vo.setAnswer (rs.getString("answer"));
						vo.setWriter(rs.getString("writer"));
						vo.seta_Writer(rs.getString("a_writer"));
						vo.setWriteDate(rs.getString("writeDate"));
						vo.seta_WriteDate(rs.getString("a_writeDate"));
						vo.setHit(rs.getInt("hit"));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("게시판 글보기를 처리하는 중 오류가 발생하였습니다.");
			} finally {
				// 7. 닫기
				DBInfo.close(conn, pstmt, rs);
			}
			// 데이터가 잘 들어가 있는지 확인한다.
//			System.out.println("QnADAO.view().vo : " + vo);
			
			return vo;
		}
		
		// 조회수 1 증가 시키는 메서드 -> update
		public int increaseHit(int no) throws Exception {
//			System.out.println("QnADAO.increaseHit() " + no);
			int result = 0;
			
			// 필요한 객체 선언 - insert, update, delete : conn, pstmt
			Connection conn = null;			// 연결
			PreparedStatement pstmt = null;	// 실행
			
			// DB에서 데이터 수정
			try {
				// 1. 드라이버 확인 + 2. 연결 객체
				conn = DBInfo.getConnection();
				// 3. sql
				String sql = "update qnaboard set hit = hit + 1 where no = ?";
				// 4. 실행 객체 + 데이터셋팅(?)
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				// 5. 실행 - select : executeQuery()
				//		 - insert, update, delete : executeUpdate()
				result = pstmt.executeUpdate();
				// 6. 표시 - vo에 데이터 담기 (한개-반복문 사용하지 않는다.)
				if(result == 0)	// 수정이 정상적으로 일어나지 않은 경우 - 조건이 안 맞다.
					throw new Exception();
//				System.out.println("QnADAO.increaseHit() : 조회수 1 증가");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("게시판 글보기를 처리하는 중 오류가 발생하였습니다.");
			} finally {
				// 7. 닫기
				DBInfo.close(conn, pstmt);
			}
			
			return result;
		}
		
		//게시판 글쓰기
		public int write(QnAVO vo) throws Exception{
//			System.out.println("QnADAO.write() " + vo);
			int result = 0;
			
			// 필요한 객체 선언 - insert,update, delete : con, pstmt
			Connection con = null; // 연결
			PreparedStatement pstmt = null; // 실행
				
			// DB에서 데이터 수정
			try {
				// 1.드라이버 확인 + 2. 연결 객체
				con = DBInfo.getConnection();
				// 3. sql
				String sql ="insert into qnaboard(no, title, question, writer, pw) values(qna_seq.nextval, ?, ?, ?, ?)";
				// 4. 실행객체 + 데이터셋팅(?)
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getQuestion());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getPW());
				// 5. 실행 - select : executeQuery()
				//       - insert,update,delete : executeUpdate()
				result = pstmt.executeUpdate();
				// 6. 표시 -> vo에 데이터 담기(한개-반복문 사용하지 않는다.)
				if(result == 0) // 쓰기가 정상적으로 일어나지 않은 경우 - 조건이 안 맞다.
					throw new Exception();
				System.out.println("성공적으로 글쓰기 완료");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception
				("게시판 글쓰기를 처리하는 중 오류가 발생하였습니다.");
			} finally {
				//7. 닫기
				DBInfo.close(con, pstmt);
			}
				return result;
		}

		// 게시판 글 삭제
		public int delete(int no, String pw) throws Exception {
//			System.out.println("QnADAO().delete " + no + " / " + pw);
			int result = 0;
				
			// 필요한 객체 선언 - insert, update, delete : conn, pstmt
			Connection conn = null;			// 연결
			PreparedStatement pstmt = null;	// 실행
			
			// DB에서 데이터 삭제
			try {
				// 1. 드라이버 확인 + 2. 연결 객체
				conn = DBInfo.getConnection();
				// 3. sql
				String sql = "delete from qnaboard where no = ? and pw = ?";
				// 4. 실행 객체 + 데이터셋팅(?)
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, pw);
				// 5. 실행 - select : executeQuery()
				//- insert, update, delete : executeUpdate()
				result = pstmt.executeUpdate();
				// 6. 표시 - vo에 데이터 담기 (한개-반복문 사용하지 않는다.)
				System.out.println("글삭제를 진행했습니다.");
			} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new Exception("게시판 글삭제 처리하는 중 오류가 발생하였습니다.");
			} finally {
				// 7. 닫기
				DBInfo.close(conn, pstmt);
				}			
			return result;
		}
		
		// 답변 작성
		public int update(QnAVO vo) throws Exception {
//		System.out.println("QnADAO().update " + vo);
		int result = 0;
			
		// 필요한 객체 선언 - insert, update, delete : conn, pstmt
		Connection conn = null;			// 연결
		PreparedStatement pstmt = null;	// 실행
			
			// DB에서 데이터 수정
			try {
				// 1. 드라이버 확인 + 2. 연결 객체
				conn = DBInfo.getConnection();
				// 3. sql
				String sql = "update qnaboard set answer = ?, a_writer = 'admin', a_writeDate = sysdate where no = ? and pw = ?";
				// 4. 실행 객체 + 데이터셋팅(?)
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getAnswer());
				pstmt.setInt(2, vo.getNo());
				pstmt.setString(3, vo.getPW());
				// 5. 실행 - select : executeQuery()
				//		 - insert, update, delete : executeUpdate()
				result = pstmt.executeUpdate();
				// 6. 표시 - vo에 데이터 담기 (한개-반복문 사용하지 않는다.)
				if(result == 0)	// 수정이 정상적으로 일어나지 않은 경우 - 조건이 안 맞다.
					throw new Exception();
				System.out.println("답변 작성을 진행했습니다.");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("답변 작성을 처리하는 중 오류가 발생하였습니다. (권한이 없거나 정보를 확인한 후 다시 실행해보세요.)");
			} finally {
				// 7. 닫기
				DBInfo.close(conn, pstmt);
			}
			
			return result;
		}
}
