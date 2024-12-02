package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.LessonVO;

public class LessonDAO {
	public final String LESSON_SELECT = "select * from lesson";
	public final String LESSON_SELECT_SORT = "select * from lesson ORDER BY NAME";
	public final String LESSON_DELETE = "delete from lesson where no = ?";
	public final String LESSON_UPDATE = "UPDATE LESSON SET ABBRE = ?, NAME = ? WHERE NO = ? ";
	public final String LESSON_INSERT = "INSERT INTO LESSON VALUES(lesson_seq.NEXTVAL, ?, ?) ";

	public ArrayList<LessonVO> lessonSelect(LessonVO lvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는객체
		ArrayList<LessonVO> lessonList = new ArrayList<LessonVO>();

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				LessonVO lessonVO = new LessonVO(no, abbre, name);
				lessonList.add(lessonVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return lessonList;
	}

	public ArrayList<LessonVO> lessonSelectSort(LessonVO lvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는객체
		ArrayList<LessonVO> lessonList = new ArrayList<LessonVO>(); // 결과값을 다른객체전달하기 위해서 사용하는객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_SELECT_SORT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String abbre = rs.getString("ABBRE");
				String name = rs.getString("NAME");
				LessonVO lessonVO = new LessonVO(no, abbre, name);
				lessonList.add(lessonVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return lessonList;
	}

	public boolean lessonDelete(LessonVO lvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null;
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(LESSON_DELETE);
			pstmt.setInt(1, lvo.getNo());
			int count = pstmt.executeUpdate();
			if (count != 0) {
				con.commit();
				successFlag = true;
			} else {
				con.rollback();
				successFlag = false;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	public boolean lessonUpdate(LessonVO lvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_UPDATE);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());
			pstmt.setInt(3, lvo.getNo());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	public boolean lessonInsert(LessonVO lvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LESSON_INSERT);
			pstmt.setString(1, lvo.getAbbre());
			pstmt.setString(2, lvo.getName());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}
}