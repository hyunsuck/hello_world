select *
from tab;

select *
from tbl_member;

update tbl_member
set responsibility = 'User';
insert into tbl_member
values('admin', '9999', '������',  'Admin');

update tbl_member
set images='lch.jpg'
where member_id='user10';

alter table tbl_member add responsibility varchar2(30);
alter table tbl_member add images varchar2(50);

--�Խ���(�۹�ȣ, ����, ����, �ۼ���, �ۼ��Ͻ�)
create table tbl_board (
 board_no number primary key --�۹�ȣ(PK)
 ,title varchar2(300) not null --������(not null)
 ,content varchar2(1000) not null --����(not null)
 ,writer varchar2(20) not null --�ۼ���(not null)
 ,write_date date default sysdate --�ۼ��Ͻ�
);
create sequence board_seq;

insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '�۵�Ͽ���', 'ù��° �۵���Դϴ�', 'user01');
insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '�������ݿ���', '��ſ�Դϴ�', 'user02');
insert into tbl_board(board_no, title, content, writer)
values(board_seq.nextval, '��������', '�ϴ� ���̳׿�..', 'user03');

insert into tbl_board
select board_seq.nextval, title, content, writer, write_date
from tbl_board
--order by board_no desc
;
-- paging
select b.*
from   (select rownum rn, a.*
        from (select *
              from tbl_board
              where writer like '%'||'������'||'%'
              order by board_no) a) b
where b.rn > (:page - 1) * 5
and   b.rn <= (:page * 5);

select count(*) from tbl_board;

delete from tbl_board
where board_no = 8;

select *--empno, ename, job, mgr, hiredate, sal
from emp
order by empno desc;

insert into emp (empno, ename, job, mgr, hiredate, sal)
values (9998, 'ȫ�浿', 'CLERK', 7788, '2020-01-01', 1000);

update emp
set    sal = 2000
      ,deptno = 10
where empno = 9998;

delete from emp
where empno = 9999;

commit;

-- table ����.
-- �����ڵ�, ������, ����, ���ǻ�, ��������
create table tbl_book (
 book_code varchar2(5) primary key, --�����ڵ�.
 book_title varchar2(50) not null, --������.
 author varchar2(30) not null, --����.
 company varchar2(30) not null, --���ǻ�.
 price number default 1000
);
create sequence book_seq;
select book_seq.nextval from dual;

insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '�̰����ڹٴ�', '�ſ��', '�Ѻ����ǻ�', 20000);
insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, 'ȥ����', '�ſ��', '�Ѻ����ǻ�', 22000);
insert into tbl_book (book_code, book_title, author, company, price)
values(book_seq.nextval, '������', '�����', '�������ǻ�', 23000);

select *
from tbl_book
where company = nvl('', company)
order by book_code;

-- �����ͺ��̽� ���α׷���.
update tbl_book
set    book_title = nvl(?, book_title)
      ,price      = ?
      ,author     = nvl(?, author)
      ,company    = nvl(?, company)
where book_code = ?;

create table tbl_member (
 user_id varchar2(10) primary key, --����� ID
 password varchar2(30) not null, --��й�ȣ
 user_name varchar2(50) not null
);
insert into tbl_member 
values ('user01', '1111', 'ȫ�浿');
insert into tbl_member 
values ('user02', '2222', '��浿');
insert into tbl_member 
values ('user03', '3333', '�ڱ浿');

select *
from tbl_member;

