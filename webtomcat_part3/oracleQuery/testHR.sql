create sequence seq_board;  -- 시퀀스 생성

create table tbl_board(
    bno number(10, 0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate    
);    

alter table tbl_board add constraint pk_board
primary key (bno);

-- 더미데이터 
insert into tbl_board (bno, title, content, writer)
values(seq_board.nextval, '테스트 제목2', '테스트 내용2', 'user002');

select * from tbl_board;


commit;



select * from tbl_board where bno > 0 ;
commit;

