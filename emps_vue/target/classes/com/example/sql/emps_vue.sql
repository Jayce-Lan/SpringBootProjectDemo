create database emp01;

use emp01;

create table t_user(
    id int primary key auto_increment,
    username varchar(60),
    realname varchar(60),
    password varchar(60),
    sex varchar(4),
    status varchar(4),
    regsterTime timestamp
);

create table t_emp(
    id int primary key auto_increment,
    name varchar(40),
    path varchar(100),
    salary double(10, 2),
    age int
);