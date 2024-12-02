package com.kh.subjectMVCProject.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.LessonVO;

public class LessonRegisterManager {
	public Scanner sc = new Scanner(System.in);
	// 과목등록(insert)

	public void insertManager() {
		LessonDAO ldao = new LessonDAO();
		System.out.print("과목요약입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String abbre = (sc.nextLine()).trim();

		System.out.print("과목명입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String name = (sc.nextLine()).trim();

		LessonVO lvo = new LessonVO(abbre, name);
		boolean successFlag = ldao.lessonInsert(lvo);

		if (successFlag == true) {
			System.out.println(name + "과목을 입력 하였습니다.");
		} else {
			System.out.println(name + "과목을 입력 실패 하였습니다.");
		}
	}

	public void selectManager() {
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo);
		if (lessonList.size() != 0) {
			printLessonList(lessonList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	public void deleteManager() {
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();

		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo);
		if (lessonList.size() != 0) {
			printLessonList(lessonList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt((sc.nextLine()).trim());
		System.out.println("no=" + no);
		LessonVO lvo2 = new LessonVO();
		lvo2.setNo(no);
		System.out.println("lvo2=" + lvo2.toString());
		boolean successFlag = ldao.lessonDelete(lvo2);

		if (successFlag == true) {
			System.out.println(no + "번호를 삭제 하였습니다.");
		} else {
			System.out.println(no + "번호 삭제 실패하였습니다.");
		}
	}

	public void updateManager() {
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo);
		if (lessonList.size() != 0) {
			printLessonList(lessonList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		System.out.print("수정할 번호선택>>");
		int no = Integer.parseInt(sc.nextLine());

		System.out.print("수정할과목입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String abbre = (sc.nextLine()).trim();

		System.out.print("수정할과목명입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String name = (sc.nextLine()).trim();

		lvo = new LessonVO(no, abbre, name);
		boolean successFlag = ldao.lessonUpdate(lvo);

		if (successFlag == true) {
			System.out.println(no + "과목을 수정 하였습니다.");
		} else {
			System.out.println(no + "과목을 수정 실패 하였습니다.");
		}
	}

	public void selectSortManager() {
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelectSort(lvo);
		if (lessonList.size() != 0) {
			printLessonList(lessonList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	public void printLessonList(ArrayList<LessonVO> lessonList) {
		for (LessonVO data : lessonList) {
			System.out.println(data);
		}
	}

}