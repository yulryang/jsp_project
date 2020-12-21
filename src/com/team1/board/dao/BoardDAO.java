package com.team1.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.board.vo.BoardVO;
import com.team1.util.db.DBInfo;

public class BoardDAO {

	//게시판 리스트
	public List<BoardVO> list() throws Exception{
		//System.out.println("BoardDAO.list()");
		List<BoardVO> list = null;
		
		// 필요한 객체 선언 - select : con, pstmt, rs
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 실행
		ResultSet rs = null; // DB 저장
		
		// DB에서 데이터 꺼내오기
		try {
			// 1.드라이버 확인 + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. sql
			String sql 
			="select no, title, writer, "
					+ " to_char(writeDate, 'yyyy.mm.dd') writeDate,"
					+ " hit "
					+ " from board "
					+ " order by no desc ";
			// 4. 실행객체
			pstmt = con.prepareStatement(sql);
			// 5. 실행 - select : executeQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> list에 데이터 담기
			if(rs != null) {
				while(rs.next()) {
					// list 객체가 한번만 생성이 되도록 처리
					if(list == null) list = new ArrayList<>();
					// 데이터의 갯수만큼 BoardVO 객체가 생성이 되어야 한다.
					BoardVO vo = new BoardVO();
					vo.setNo(rs.getInt("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getInt("hit"));
					
					// 저장된 데이터 객체를 list에 담는다.
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception
			("게시판 리스트를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 데이터가 잘 들어 가 있는지 확인한다.
		//System.out.println("BoardDAO.list().list:"+list);
		
		return list;
	}

	//게시판 글보기
	public BoardVO view(int no) throws Exception{
		//System.out.println("BoardDAO.view().no:" + no);
		BoardVO vo = null;
		
		// 필요한 객체 선언 - select : con, pstmt, rs
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 실행
		ResultSet rs = null; // DB 저장
		
		// DB에서 데이터 꺼내오기
		try {
			// 1.드라이버 확인 + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. sql
			String sql 
			="select no, title, content, writer, "
					+ " to_char(writeDate, 'yyyy.mm.dd') writeDate,"
					+ " hit "
					+ " from board "
					+ " where no = ? ";
			// 4. 실행객체 + 데이터셋팅(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			// 5. 실행 - select : executeQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> vo에 데이터 담기(한개-반복문 사용하지 않는다.)
			if(rs != null) {
				if(rs.next()) {
					// vo 객체 생성
					vo = new BoardVO();
					vo.setNo(rs.getInt("no"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getInt("hit"));
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception
			("게시판 글보기를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		// 데이터가 잘 들어 가 있는지 확인한다.
		//System.out.println("BoardDAO.view().vo:" + vo);
		
		return vo;
	}

	//조회수 1 증가 시키는 메서드 -> update
	public int increaseHit(int no) throws Exception{
		//System.out.println("BoardDAO.increaseHit().no:" + no);
		int result = 0;
		
		// 필요한 객체 선언 - insert,update, delete : con, pstmt
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 실행
		
		// DB에서 데이터 수정
		try {
			// 1.드라이버 확인 + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. sql
			String sql 
			="update board set hit = hit + 1 "
					+ " where no = ? ";
			// 4. 실행객체 + 데이터셋팅(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			// 5. 실행 - select : executeQuery()
			//       - insert,update,delete : executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(한개-반복문 사용하지 않는다.)
			if(result == 0) // 수정이 정상적으로 일어나지 않은 경우 - 조건이 안 맞다.
				throw new Exception();
			//System.out.println("BoardDAO.increaseHit():조회수 1증가");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception
			("게시판 글보기를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	//게시판 글쓰기
	public int write(BoardVO vo) throws Exception{
		//System.out.println("BoardDAO.write().vo:" + vo);
		int result = 0;
		
		// 필요한 객체 선언 - insert,update, delete : con, pstmt
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 실행
		
		// DB에서 데이터 수정
		try {
			// 1.드라이버 확인 + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. sql
			String sql 
			="insert into board(no, title, content, writer, pw) "
					+ " values(board_seq.nextval, ?, ?, ?, ?)";
			// 4. 실행객체 + 데이터셋팅(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setString(4, vo.getPw());
			// 5. 실행 - select : executeQuery()
			//       - insert,update,delete : executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(한개-반복문 사용하지 않는다.)
			if(result == 0) // 쓰기가 정상적으로 일어나지 않은 경우 - 조건이 안 맞다.
				throw new Exception();
			//System.out.println("BoardDAO.write():성공적으로 글쓰기 완료");
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
	
	//게시판 글수정
	public int update(BoardVO vo) throws Exception{
		//System.out.println("BoardDAO.update().vo:" + vo);
		int result = 0;
		
		// 필요한 객체 선언 - insert,update, delete : con, pstmt
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 실행
		
		// DB에서 데이터 수정
		try {
			// 1.드라이버 확인 + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. sql
			String sql 
			="update board "
				+ " set title = ?, content = ?, writer = ? "
				+ " where no = ? and pw = ?";
			// 4. 실행객체 + 데이터셋팅(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getNo());
			pstmt.setString(5, vo.getPw());
			// 5. 실행 - select : executeQuery()
			//       - insert,update,delete : executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(한개-반복문 사용하지 않는다.)
			//System.out.println("BoardDAO.write():글수정을 진행했습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception
			("게시판 글수정를 처리하는 중 오류가 발생하였습니다.(정보를 확인한 후 다시 실행)");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	//게시판 글삭제
	public int delete(int no, String pw) throws Exception{
		//System.out.println("BoardDAO.delete().no/pw:"
			//	+ no + "/" + pw);
		int result = 0;
		
		// 필요한 객체 선언 - insert,update, delete : con, pstmt
		Connection con = null; // 연결
		PreparedStatement pstmt = null; // 실행
		System.out.println();
		// DB에서 데이터 삭제
		try {
			// 1.드라이버 확인 + 2. 연결 객체
			con = DBInfo.getConnection();
			// 3. sql
			String sql ="delete from board"
					+ " where no = ? and pw = ?";
			// 4. 실행객체 + 데이터셋팅(?)
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, pw);
			// 5. 실행 - select : executeQuery()
			//       - insert,update,delete : executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기(한개-반복문 사용하지 않는다.)
			//System.out.println("BoardDAO.delete():글삭제을 진행했습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception
			("게시판 글삭제를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			//7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	

}
