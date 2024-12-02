# subjectMVCProject

자바 (Eclipse)와 Oracle DB를 사용한 학과 수강신청 관리 시스템

## 🖥️ 프로젝트 소개

이 프로젝트는 학과, 학생, 과목, 수강신청 정보를 관리할 수 있는 시스템입니다. 학생들이 수강할 과목을 신청하고, 학과와 관련된 정보를 조회, 수정할 수 있는 기능을 제공합니다. 이 시스템은 학과, 학생, 과목에 대한 관리 및 수강신청 기능을 포함하고 있습니다.

## 🕰️ 개발 기간

- 2023.11.20일 - 2023.11.24일

### 🧑‍🤝‍🧑 개발자

- 김연아 - 프로젝트 설계, 데이터베이스 구축, 기능 구현, 통합 관리

### ⚙️ 개발 환경

- `Java 17`
- **IDE**: Eclipse
- **Database**: Oracle SQL Developer
- **jdk**: 21.0.4

## 📌 주요 기능

#### 1. 학과 관리

- 학과 정보 등록, 수정, 삭제
- 학과 정보 조회 및 목록 표시
- 학과 정보 정렬 기능

#### 2. 학생 관리

- 학생 정보 등록, 수정, 삭제
- 학생 정보 조회 및 목록 표시
- 학생의 학과 정보 조회

#### 3. 과목 관리

- 과목 정보 등록, 수정, 삭제
- 과목 정보 조회 및 목록 표시
- 과목 정보 정렬 기능

#### 4. 수강 신청 관리

- 학생이 과목을 선택하여 수강 신청
- 수강 신청 내역 조회 및 취소
- 수강 신청 조인 목록 조회

#### 5. ERD

<img src="https://github.com/KimYeonA1/subjectMVCProject/blob/main/Image/subjectMVCProject_erd.png" width = "800px" height = "450px">

---

## 🧩 데이터베이스 스키마

프로젝트에서 사용된 주요 테이블과 관계는 아래와 같습니다:

- **SUBJECT**: 학과 정보 관리
- **STUDENT**: 학생 정보 관리
- **LESSON**: 과목 정보 관리
- **TRAINEE**: 수강신청 관리

### 📜 DB 테이블 생성 SQL

```sql
-- 학과 테이블 생성
CREATE TABLE SUBJECT (
    NO NUMBER,                 -- PK, 시퀀스
    NUM VARCHAR2(2) NOT NULL,  -- 학과 번호 (01, 02, 03, 04, 05)
    NAME VARCHAR2(24) NOT NULL -- 학과 이름
);
ALTER TABLE SUBJECT ADD CONSTRAINT SUBJECT_NO_PK PRIMARY KEY (NO);
ALTER TABLE SUBJECT ADD CONSTRAINT SUBJECT_NUM_UK UNIQUE (NUM);

CREATE SEQUENCE SUBJECT_SEQ
START WITH 1
INCREMENT BY 1;
```

```sql
-- 학생 테이블 생성
CREATE TABLE STUDENT (
    NO NUMBER,                    -- PK, 시퀀스
    NUM VARCHAR2(8) NOT NULL,     -- 학번 (년도 + 학과번호)
    NAME VARCHAR2(12) NOT NULL,   -- 이름
    ID VARCHAR2(12) NOT NULL,     -- 아이디
    PASSWD VARCHAR2(12) NOT NULL, -- 패스워드
    S_NUM VARCHAR2(2),            -- 학과번호 (FK)
    BIRTHDAY VARCHAR2(8) NOT NULL,-- 생년월일
    PHONE VARCHAR2(15) NOT NULL,  -- 전화번호
    ADDRESS VARCHAR2(80) NOT NULL,-- 주소
    EMAIL VARCHAR2(40) NOT NULL,  -- 이메일
    SDATE DATE DEFAULT SYSDATE    -- 등록일
);
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_NO_PK PRIMARY KEY (NO);
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_ID_UK UNIQUE (ID);
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_NUM_UK UNIQUE (NUM);
ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_SUBJECT_NUM_FK
    FOREIGN KEY (S_NUM) REFERENCES SUBJECT(NUM) ON DELETE SET NULL;
CREATE SEQUENCE STUDENT_SEQ
START WITH 1
INCREMENT BY 1;
```

```sql
-- 과목 테이블 생성
CREATE TABLE LESSON (
    NO NUMBER,                 -- PK, 시퀀스
    ABBRE VARCHAR2(2) NOT NULL, -- 과목 요약
    NAME VARCHAR2(40) NOT NULL  -- 과목 이름
);
ALTER TABLE LESSON ADD CONSTRAINT LESSON_NO_PK PRIMARY KEY (NO);
ALTER TABLE LESSON ADD CONSTRAINT LESSON_ABBRE_UK UNIQUE (ABBRE);

CREATE SEQUENCE LESSON_SEQ
START WITH 1
INCREMENT BY 1;
```

```sql
-- 수강신청 테이블 생성
CREATE TABLE TRAINEE (
    NO NUMBER,                    -- PK, 시퀀스
    S_NUM VARCHAR2(8),             -- 학생 번호 (FK)
    ABBRE VARCHAR2(2),             -- 과목 요약 (FK)
    SECTION VARCHAR2(20) NOT NULL, -- 전공, 부전공, 교양
    REGDATE DATE DEFAULT SYSDATE   -- 수강 신청일
);
ALTER TABLE TRAINEE ADD CONSTRAINT TRAINEE_NO_PK PRIMARY KEY (NO);
ALTER TABLE TRAINEE ADD CONSTRAINT TRAINEE_STUDENT_NUM_FK
    FOREIGN KEY (S_NUM) REFERENCES STUDENT(NUM) ON DELETE SET NULL;
ALTER TABLE TRAINEE ADD CONSTRAINT TRAINEE_LESSON_ABBRE_FK
    FOREIGN KEY (ABBRE) REFERENCES LESSON(ABBRE) ON DELETE SET NULL;

CREATE SEQUENCE TRAINEE_SEQ
START WITH 1
INCREMENT BY 1;

COMMIT;
```
