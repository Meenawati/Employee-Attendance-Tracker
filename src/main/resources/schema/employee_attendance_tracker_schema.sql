drop database employee_attendance_tracker;

create database employee_attendance_tracker;

use employee_attendance_tracker;

create table employee_detail(
	employee_id int auto_increment primary key,
    name varchar(25),
    department varchar(25),
    index(name)
    );
    
create table vacation_type(
	vacation_id int auto_increment primary key,
	vacation_type varchar(20),
	total_days int(5)
);

create table vacation_detail(
	vacation_detail_id int auto_increment primary key,
	employee_id int,
	foreign key (employee_id) references employee_detail(employee_id),
	vacation_id int,
	foreign key (vacation_id) references vacation_type(vacation_id),
	Date date,
	index(employee_id, vacation_id, Date)
);
    
INSERT INTO vacation_type (vacation_type, total_days) VALUES ('VACATION', 10);
INSERT INTO vacation_type (vacation_type, total_days) VALUES ('SICK', 10);
INSERT INTO vacation_type (vacation_type, total_days) VALUES ('PARENTAL', 5);
INSERT INTO vacation_type (vacation_type, total_days) VALUES ('MARRIAGE', 5);