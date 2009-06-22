use wateon;
create table message(number int not null auto_increment primary key, sender varchar(50) not null, receiver varchar(50) not null, message varchar(5000) not null, date date);
create table imessage(number int not null auto_increment primary key, sender varchar(50) not null, receiver varchar(50) not null, message varchar(5000) not null, date date);