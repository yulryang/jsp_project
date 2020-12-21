package com.team1.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBInfo {


	//접속정보
	private static final String URL = "jdbc:oracle:thin:@192.168.56.159:1521:orcl";
	private static final String ID = "team1";
	private static final String PW = "team1";
	
	//Oracle Driver -> 오라클 명령을 실행하기 위한 프로그램
	public static final String DRIVER
	="oracle.jdbc.driver.OracleDriver";//대소문자 구분
	//드라이버를 확인해서 정보를 저장하는 변수
	private static boolean checkDriver;
	//클래스에 맨 처음 접근할 때 한번만 실행이 된다.
	static {
		try {
			Class.forName(DRIVER);
			checkDriver = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			checkDriver = false;
			e.printStackTrace();
		}
	}
	//연결 객체를 주는 메서드
	public static final Connection getConnection() 
			throws Exception{
		if(checkDriver == false)
			throw new Exception("드라이버가 존재하지 않습니다.");
		return DriverManager.getConnection(URL, ID, PW);
	}
	//
	// 사용한 객체를 닫기 - select
	public static final void 
	close(Connection con, PreparedStatement pstmt, ResultSet rs)
	throws Exception{
		close(con, pstmt);
		if(rs != null) rs.close();
	}
	
	// 사용한 객체를 닫기 - insert, update, delete
	public static final void 
	close(Connection con, PreparedStatement pstmt)
			throws Exception{
		if(con != null) con.close();
		if(pstmt != null) pstmt.close();
	}
}