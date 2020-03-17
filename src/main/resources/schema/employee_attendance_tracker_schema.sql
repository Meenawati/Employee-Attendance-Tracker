
create database employee_attendance_tracker;

use employee_attendance_tracker;

create table employee_detail(
	employee_id int auto_increment primary key,
    name varchar(25),
    age int(3),
    department varchar(25),
    salary decimal(10,2),
    index(name)
    );
    
create table vacation_type(
vacation_id int auto_increment primary key,
vacation_type varchar(20),
total_days int(5)
);

create table vacation_detail(
employee_id int,
foreign key (employee_id) references employee_detail(employee_id),
vacation_id int,
foreign key (vacation_id) references vacation_type(vacation_id),
Date date,
days smallint,
index(employee_id, vacation_id, Date)
);
    