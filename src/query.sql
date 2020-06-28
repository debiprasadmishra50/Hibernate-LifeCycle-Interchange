show databases;
use hb_student_tracker;
show tables;

drop table employee;

Create table employee (
	id INT Primary Key Auto_increment,
    empname varchar(45) default null
)ENGINE=MyISAM;

desc employee;

select * from employee;

desc hibernate_sequence;

drop table hibernate_sequence;

select * from hibernate_sequence;