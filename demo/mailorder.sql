-- CREATE DATABASE IF NOT EXISTS mailorder;
USE mailorder;

drop table IF EXISTS directedby;
create table directedby(
	id	int(5),
	pno int(5),
	primary key (id,pno)
);  

drop table IF EXISTS director;
create table director(
	id		int(5) primary key,
	name	varchar(20)
);

drop table IF EXISTS odetails;
create table odetails (
  ono      int(5) not null,
  pno      int(5) not null,
  qty      int(6),
  primary key (ono,pno));

drop table IF EXISTS parts;
create table parts(
  pno      int (5) not null primary key,
  pname    varchar(30),
  qoh      int(5),
  price    double(6,2),
  olevel   int(4));

drop table IF EXISTS orders;
create table orders (
  ono      int(5) primary key,
  cno      int(5),
  eno      int(4),
  received date,
  shipped  date);
    
drop table IF EXISTS customers;
create table customers (
  cno      int(5) not null primary key,
  cname    varchar(30),
  street   varchar(30),
  zip      int(5),
  phone    char(12)
  );

drop table IF EXISTS employees;
create table employees (
  eno      int (4) not null primary key, 
  ename    varchar(30),
  zip      int(5),
  hdate    date);

drop table IF EXISTS zipcodes;
create table zipcodes (
  zip      int (5) PRIMARY KEY,
  city     varchar(30)
  );

ALTER TABLE directedby ADD CONSTRAINT `dir_pno_PK` FOREIGN KEY (`pno`) REFERENCES `parts` (`pno`);
ALTER TABLE directedby ADD CONSTRAINT `dir_id_PK` FOREIGN KEY (`id`) REFERENCES `director` (`id`);
ALTER TABLE customers ADD CONSTRAINT `cus_zip_PK` FOREIGN KEY (`zip`) REFERENCES `zipcodes` (`zip`);
ALTER TABLE employees ADD CONSTRAINT `emp_zip_PK` FOREIGN KEY (`zip`) REFERENCES `zipcodes` (`zip`);
ALTER TABLE orders ADD CONSTRAINT `ord_cno_PK` FOREIGN KEY (`cno`) REFERENCES `customers` (`cno`);
ALTER TABLE orders ADD CONSTRAINT `ord_eno_PK` FOREIGN KEY (`eno`) REFERENCES `employees` (`eno`);
ALTER TABLE odetails ADD CONSTRAINT `ode_ono_PK` FOREIGN KEY (`ono`) REFERENCES `orders` (`ono`);
ALTER TABLE odetails ADD CONSTRAINT `ode_pno_PK` FOREIGN KEY (`pno`) REFERENCES `parts` (`pno`);

commit;

-- create database mailorder;

USE mailorder;
insert into  zipcodes values
  (67226,'Wichita');
insert into  zipcodes values
  (60606,'Fort Dodge');
insert into  zipcodes values
  (50302,'Kansas City');
insert into  zipcodes values
  (54444,'Columbia');
insert into  zipcodes values
  (66002,'Liberal');
insert into  zipcodes values
  (61111,'Fort Hays');

insert into employees values
  (1000,'Jones',67226,'1995-12-12');
insert into employees values
  (1001,'Smith',60606,'1992-01-01');
insert into employees values
  (1002,'Brown',50302,'1944-09-01');
insert into employees values
  (1003,'Summer',67226,'2000-11-12');
insert into employees values
  (1004,'Jones',50302,'2012-06-12');
insert into employees values
  (1005,'Doe',67226,'2012-12-11');

insert into parts values
  (10506,'Land Before Time I',200,19.99,20);
insert into parts values
  (10507,'Land Before Time II',156,19.99,20);
insert into parts values
  (10508,'Land Before Time III',190,19.99,20); 
insert into parts values
  (10509,'Land Before Time IV',60,19.99,20);
insert into parts values
  (10601,'Sleeping Beauty',300,24.99,20);
insert into parts values
  (10701,'When Harry Met Sally',120,19.99,30);
insert into parts values
  (10800,'Dirty Harry',140,14.99,30);
insert into parts values
  (10900,'Dr. Zhivago',100,24.99,30);

insert into customers values
  (1111,'Charles','123 Main St.',67226,'316-636-5555');
insert into customers values
  (2222,'Bertram','237 Ash Avenue',67226,'316-689-5555');
insert into customers values
  (3333,'Barbara','111 Inwood St.',60606,'316-111-1234');
insert into customers values
  (4444,'Mann','2 Main St.',67226,'316-636-5533');
insert into customers values
  (5555,'Stone','23 Main St.',67226,'316-636-5376');
insert into customers values
  (6666,'Gonzales','123 Park Road',67226,'316-587-5555');

insert into orders values
  (1020,1111,1000,'1994-12-10','1994-12-12');
insert into orders values
  (1021,1111,1000,'1995-12-01','1995-01-15');
insert into orders values
  (1022,2222,1001,'1995-02-13','1995-02-20');
insert into orders values
  (1023,3333,1001,'1995-02-13','1995-02-20');
insert into orders values
  (1024,2222,1002,'1995-02-13','1995-02-20');
insert into orders values
  (1025,3333,1002,'1995-02-13','1995-02-20');
insert into orders values
  (1026,3333,1002,'1995-02-14', NULL);


insert into odetails values
  (1020,10506,1);
insert into odetails values
  (1020,10507,1);
insert into odetails values
  (1020,10508,2);
insert into odetails values
  (1020,10509,3);
insert into odetails values
  (1021,10601,4);
insert into odetails values
  (1022,10601,1);
insert into odetails values
  (1022,10701,1);
insert into odetails values
  (1023,10800,1);
insert into odetails values
  (1023,10900,1);

insert into director values(1, 'Don Bluth');
insert into director values(2, 'Roy Allen Smith');
insert into director values(3, 'David Irving');
insert into director values(4, 'Rob Reiner');
insert into director values(5, 'Don Siegel');
insert into director values(6, 'David Lean');
insert into director values(7, 'Lars Von Trier');

insert into directedby values (1, 10506);
insert into directedby values (2, 10507);
insert into directedby values (2, 10508);
insert into directedby values (2, 10509);
insert into directedby values (3, 10601);
insert into directedby values (4, 10701);
insert into directedby values (5, 10800);
insert into directedby values (6, 10900);
insert into directedby values (7, 10900);


commit;