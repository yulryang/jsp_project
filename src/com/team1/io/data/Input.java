package com.team1.io.data;

import java.util.Scanner;

/*
 * 데이터를 입력받는 클래스
 * 
 */
public class Input {
	
	private static final Scanner SCANNER = new Scanner(System.in);
	
	//문자열을 받아내는 메서드
	public static final String getString(){
		return SCANNER.nextLine();
		
	}
	//입력 데이터의 안내문을 출력하고 문자열을 받아내는 메서드
	public static final String getString(String msg) {
		System.out.print(msg + " -> ");
		return getString();
	}
	
	//int 데이터를 받아내는 메서드
	public static final int getInt() throws Exception{
		return Integer.parseInt(getString());
	}
	
	//int 데이터를 메세지 안내와 함께 받아내는 메서드
	public static final int getInt(String msg) {
		//숫자 데이터를 입력할 때까지 무한반복 시킨다.
		while(true) {
			try {
				System.out.print(msg+" ->");
				return getInt();
			}catch(Exception e) {
				System.out.println("[- 숫자만 입력 가능합니다.(0-9) -]");
			}
		}
	}
}

