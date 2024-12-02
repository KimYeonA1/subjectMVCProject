-- DROP 기존 테이블 및 시퀀스
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE SUBJECT CASCADE CONSTRAINTS';  --테이블
    EXECUTE IMMEDIATE 'DROP TABLE STUDENT CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE TRAINEE CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE LESSON CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP SEQUENCE SUBJECT_SEQ';              --시퀀스
    EXECUTE IMMEDIATE 'DROP SEQUENCE STUDENT_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE LESSON_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE TRAINEE_SEQ';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- 학과 (01-컴퓨터학과 / 02-교육학과 / 03-신문방송학과 / 04-인터넷비즈니스과 / 05-기술경영과)
create table subject( 
    no number,                 -- pk, seq
    num varchar2(2) not null,  -- 학과번호 01, 02, 03,04,05
    name varchar2(24) not null -- 학과이름
);
Alter table subject add constraint subject_no_pk primary key(no); 
Alter table subject add constraint subject_num_uk UNIQUE(num);

create sequence subject_seq
start with 1
increment by 1; 

-- 학생
create table student( 
    no number,                    --pk, seq
    num varchar2(8) not null,     --학번(년도학과번호)
    name varchar2(12) not null,   --이름
    id varchar2(12) not null,     --아이디
    passwd varchar2(12) not null, --패스워드
    s_num varchar2(2) ,           --학과번호(fk)
    birthday varchar2(8) not null,--생년월일 
    phone varchar2(15) not null,  --전화번호
    address varchar2(80) not null,--주소
    email varchar2(40) not null,  --이메일
    sdate date default sysdate    --등록일
);
Alter table student add constraint student_no_pk primary key(no); 
Alter table student add constraint student_id_uk UNIQUE(id);
Alter table student add constraint student_num_uk UNIQUE(num);
Alter table student add constraint student_subject_num_fk 
    FOREIGN key(s_num) References subject(num) on delete set null;
alter table student drop constraint student_subject_num_fk;

create sequence student_seq
start with 1
increment by 1; 
-- SUBJECT STUDENT INNER JOIN
SELECT STU.NO, STU.NUM, STU.NAME, STU.ID,PASSWD,STU.S_NUM,SUB.NAME AS SUBJECT_NAME ,BIRTHDAY,PHONE,ADDRESS, EMAIL, SDATE
FROM STUDENT STU INNER JOIN SUBJECT SUB ON STU.S_NUM = SUB.NUM ; 

-- lesson 과목
create table lesson( 
    no number ,                 --pk seq
    abbre varchar2(2) not null, --과목요약
    name varchar2(40) not null  --과목이름
);
Alter table lesson add constraint lesson_no_pk primary key(no); 
Alter table lesson add constraint lesson_abbre_uk UNIQUE(abbre);

create sequence lesson_seq 
start with 1
increment by 1;

-- trainee 수강신청
create table trainee( 
    no number ,                     --pk seq
    s_num varchar2(8),              --student.num(fk) 학생번호
    abbre varchar2(2),              --lesson.abbre(fk) 과목요약
    section varchar2(20) not null,  --전공,부전공,교양
    regdate date default sysdate      --수강신청일
);
select T.no, T.section, T.regdate, S.num, S.name, L.abbre, L.name as abbreName from trainee T inner join student S on T.s_num = S.num
                       inner join lesson L on T.abbre = L.abbre 
         order by T.no; 

Alter table trainee add constraint trainee_no_pk primary key(no);
Alter table trainee add constraint trainee_student_num_fk 
    FOREIGN key(s_num) References student(num) on delete set null;
Alter table trainee add constraint trainee_lesson_abbre_fk 
    FOREIGN key(abbre) References lesson(abbre) on delete set null;
Alter table trainee drop constraint trainee_lesson_abbre_fk; 
    
create sequence trainee_seq 
start with 1
increment by 1;

COMMIT;