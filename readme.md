create schema if not exists phonebook;

use phonebook;

create table if not exists user (
  id int(11) auto_increment primary key,
  login varchar(10) not null,
  pass varchar(10) not null,
  name varchar(10) not null
);

create table if not exists contact (
  id int(11) auto_increment primary key,
  firstname varchar(10) not null,
  lastname varchar(10),
  middlename varchar(10),
  mobile varchar(15),
  home varchar(15),
  address varchar(35),
  email varchar(25),
  user_id int(11),
  foreign key userid(user_id)
  references user(id)
);

