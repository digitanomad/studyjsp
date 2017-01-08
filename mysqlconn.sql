create table member(
	id	varchar(50)	not null primary key,
	passwd	varchar(16)	not null,
	name	varchar(10)	not null,
	reg_date	datetime	not null
);

desc member;

create table test(
	num_id	int	not null primary key auto_increment,
	title	varchar(50)	not null,
	content	text	not null
);

desc test;

alter table member
	add (address varchar(100) not null,
		tel varchar(20) not null);

drop table test;

show tables;

insert into member(id, passwd, name, reg_date, address, tel) 
values('kingdora@dragon.com','1234','김개동', now(), '서울시', '010-1111-1111');

insert into member(id, passwd, name, reg_date, address, tel) 
values('hongkd@aaa.com','1111','홍길동', now(), '경기도', '010-2222-2222');

select *
from member;

select id, passwd
from member;

select id, passwd
from member
where id = 'hongkd@aaa.com';


select *
from member
where id = 'hongkd@aaa.com';

update member
set passwd='3579'
where id = 'hongkd@aaa.com';

delete from member
where id = 'hongkd@aaa.com';

alter table member modify passwd varchar(60) not null;




create table board(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	subject varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date datetime not null,
	ip varchar(30) not null,
	readcount int default 0,
	ref int not null,
	re_step smallint not null,
	re_level smallint not null
);

select *
from board;