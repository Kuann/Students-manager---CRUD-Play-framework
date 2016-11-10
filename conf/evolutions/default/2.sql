# --- Sample dataset

# --- !Ups

insert into course (id,name) values (1,'Data Mining');
insert into course (id,name) values (2,'Thinking in Java');
insert into course (id,name) values (3,'OOP in CPP');
insert into course (id,name) values (4,'NodeJs programming');
insert into course (id,name) values (5,'React native');
insert into course (id,name) values (6,'Java advanced');
insert into course (id,name) values (7,'Computer networking');



insert into student (id,name,course_id) values (1,'Donald Trump',null);
insert into student (id,name,course_id) values (2,'Juan Mata',1);
insert into student (id,name,course_id) values (3,'Neymar Jr',2);
insert into student (id,name,course_id) values (4,'Bill Gate',1);

# --- !Downs

delete from course;
