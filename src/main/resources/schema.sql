create table department(
  department_name varchar(255) primary key
);

create table employee(
  name varchar(255) primary key,
  salary_employee decimal(10,2) not null,
  department_name varchar(128) not null
);


ALTER TABLE employee ADD FOREIGN KEY (department_name) REFERENCES department(department_name);

