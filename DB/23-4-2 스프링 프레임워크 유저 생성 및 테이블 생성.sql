

drop table testtb;

create user homespringuser@localhost identified by '8615'; -- 유저 생성 및 비밀번호 설정 

grant all privileges on homestudy.* to homespringuser@localhost;  -- 권한주기

use homestudy;

-- 회원 테이블

create table member_table(
	id bigint primary key auto_increment,
    memberEmail varchar(20) unique,
    memberPassword varchar(20),
    memberName varchar(20),
    memberAge int,
    memberMobile varchar(30)
);

