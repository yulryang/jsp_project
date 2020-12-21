package com.team1.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team1.notice.vo.NoticeVO;
import com.team1.util.db.DBInfo;

public class NoticeDAO {

	// 게시판 리스트
	public List<NoticeVO> list() throws Exception {
		List<NoticeVO> list = null;

		// 필요한 객체 선언- select : con, pstmt, rs

		Connection con = null;// 연결
		PreparedStatement pstmt = null;// 실행
		ResultSet rs = null;// DB저장

		// DB에서 데이터 꺼내오기
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3.sql
			String sql = "select no, title, to_char(startDate,'yyyy.mm.dd') startDate"
			+ ",to_char(endDate,'yyyy.mm.dd') endDate "
			+ ",to_char(writeDate,'yyyy.mm.dd') writeDate"
			+ " from notice order by no desc";
			// 4. 실행객체
			pstmt = con.prepareStatement(sql);
			// 5. 실행 - select: excuteQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> list에 데이터 담기
			if (rs != null) {
				while (rs.next()) {
					// list 객체가 한번만 생성이 되도록 처리
					if (list == null)
						list = new ArrayList<NoticeVO>();
					// 데이터의 개수만큼 BoardVo 객체가 생성이 되어야 한다.

					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getInt("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setStartDate(rs.getString("StartDate"));
					vo.setEndDate(rs.getString("EndDate"));
					// 리스트에 담기
					// 저장된 데이터 객체를 list에 담는다.
					list.add(vo);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception("공지사항 리스트를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			// .닫기
			DBInfo.close(con, pstmt, rs);
		}

		return list;
	}

	// 게시판 글보기
	public NoticeVO view(int no) throws Exception {
		//System.out.println("BoardDAO.View().no"+no);
		NoticeVO vo = null;

		// 필요한 객체 선언- select : con, pstmt, rs

		Connection con = null;// 연결
		PreparedStatement pstmt = null;// 실행
		ResultSet rs = null;// DB저장

		// DB에서 데이터 꺼내오기
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3.sql
			String sql = "select no, title, content, " 
						+ "to_char(writeDate,'yyyy.mm.dd') writeDate,"
						+ "to_char(endDate,'yyyy.mm.dd') endDate, " 
						+ "to_char(startDate,'yyyy.mm.dd') startDate"
						+ " from notice where no = ?";
			// 4. 실행객체 + ?인 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);//1의 의미 -> 첫번째 물음표
			// 5. 실행 - select: excuteQuery()
			rs = pstmt.executeQuery();
			// 6. 표시 -> vo에 데이터 담기 (한 개의 데이터 -> 반복문을 사용하지 않는다.)
			if (rs != null) {
				if (rs.next()) {
					// vo 객체 생성             
					
					vo = new NoticeVO();
					vo.setNo(rs.getInt("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setContent(rs.getString("content"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setStartDate(rs.getString("startDate"));
					// 리스트에 담기
					// 저장된 데이터 객체를 list에 담는다.
					//list.add(vo);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception("공지사항 글보기를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			// 7.닫기
			DBInfo.close(con, pstmt, rs);
		}
		//데이터가 잘 들어가 있는지 확인
		//System.out.println("noticeDAO.view().vo"+vo);
		return vo;
	}

	//조회수 1 증가 시키는 매서드 -> update
//	public int increaseHit(int no) throws Exception {
//		System.out.println("noticeDAO.increateHit().no"+no);
//		int result = 0;
//
//		// 필요한 객체 선언- insert, update,delete: con, pstmt, rs
//
//		Connection con = null;// 연결
//		PreparedStatement pstmt = null;// 실행
//
//		// DB에서 데이터 꺼내오기
//		try {
//			// 1. 드라이버 확인 + 2. 연결객체
//			con = DBInfo.getConnection();
//			// 3.sql
//			String sql = "update notice set hit = hit +1 where no = ?";
//			// 4. 실행객체 + ?인 데이터 세팅
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, no);//1의 의미 -> 첫번째 물음표
//			// 5. 실행 - select: excuteQuery()
//			//       - insert, update, delete: executeUpdate()
//			result = pstmt.executeUpdate();
//			// 6. 표시 -> vo에 데이터 담기 (한 개의 데이터 -> 반복문을 사용하지 않는다.)
//			if(result == 0)//수정이 정상적으로 일어나지 않은 경우
//				throw new Exception();
//			System.out.println("noticeDAO.increaseHit(): 조회수 1 증가");
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			throw new Exception("공지사항 글보기를 처리하는 중 오류가 발생하였습니다.");
//		} finally {
//			// 7.닫기
//			DBInfo.close(con, pstmt);
//		}
//		//데이터가 잘 들어가 있는지 확인
//		return result;
//	}

	public int write(NoticeVO vo) throws Exception {
		int result = 0;

		// 필요한 객체 선언- insert, update,delete: con, pstmt, rs

		Connection con = null;// 연결
		PreparedStatement pstmt = null;// 실행

		// DB에서 데이터 꺼내오기
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3.sql
			String sql = "insert into notice(no,title,content,startDate,endDate) values(notice_seq.nextval, ?, ?, ?, ?)";
			// 4. 실행객체 + ?인 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());//1의 의미 -> 첫번째 물음표
			pstmt.setString(2, vo.getContent());
			if (vo.getStartDate()==null)
				pstmt.setString(3, "default");
			else pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			// 5. 실행 - select: excuteQuery()
			//       - insert, update, delete: executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기 (한 개의 데이터 -> 반복문을 사용하지 않는다.)
			if(result == 0)//수정이 정상적으로 일어나지 않은 경우
				throw new Exception();
			System.out.println("글쓰기 완료");
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception("공지사항 글쓰기를 처리하는 중 오류가 발생하였습니다.");
		} finally {
			// 7.닫기
			DBInfo.close(con, pstmt);
		}
		//데이터가 잘 들어가 있는지 확인
		return result;
	}
	
	//게시판 글 수정
	public int update(NoticeVO vo) throws Exception {
		//System.out.println("BoardDAO.update().vo"+vo);
		int result = 0;

		// 필요한 객체 선언- insert, update,delete: con, pstmt, rs

		Connection con = null;// 연결
		PreparedStatement pstmt = null;// 실행

		// DB에서 데이터 꺼내오기
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3.sql
			String sql = "update notice set title = ?, content=?,startDate=?,endDate=? where no=? ";
			// 4. 실행객체 + ?인 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());//1의 의미 -> 첫번째 물음표
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			
			pstmt.setInt(5, vo.getNo());
			
			// 5. 실행 - select: excuteQuery()
			//       - insert, update, delete: executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기 (한 개의 데이터 -> 반복문을 사용하지 않는다.)
			if(result == 0)//수정이 정상적으로 일어나지 않은 경우
				throw new Exception();
			System.out.println("수정이 완료되었습니다.");
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception("수정하는 중 오류가 발생하였습니다. 정보를 확인한 후 다시 실행해 주세요.");
		} finally {
			// 7.닫기
			DBInfo.close(con, pstmt);
		}
		//데이터가 잘 들어가 있는지 확인
		return result;
	}
	
	//게시판 글 삭제
	public int delete(int no) throws Exception {
		//System.out.println("BoardDAO.delete()"+no);
		int result = 0;

		// 필요한 객체 선언- insert, update,delete: con, pstmt, rs

		Connection con = null;// 연결
		PreparedStatement pstmt = null;// 실행

		// DB에서 데이터 꺼내오기
		try {
			// 1. 드라이버 확인 + 2. 연결객체
			con = DBInfo.getConnection();
			// 3.sql
			String sql = "delete from notice where no=?";
			// 4. 실행객체 + ?인 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
		
			// 5. 실행 - select: excuteQuery()
			//       - insert, update, delete: executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 표시 -> vo에 데이터 담기 (한 개의 데이터 -> 반복문을 사용하지 않는다.)
			if(result == 0)//수정이 정상적으로 일어나지 않은 경우
				throw new Exception();
			System.out.println("글이 삭제되었습니다.");
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception("삭제하는 중 오류가 발생하였습니다.");
		} finally {
			// 7.닫기
			DBInfo.close(con, pstmt);
		}
		//데이터가 잘 들어가 있는지 확인
		return result;
	}

}
