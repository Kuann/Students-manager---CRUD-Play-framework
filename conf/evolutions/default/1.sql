# --- First database schema

# --- !Ups

create table course (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_course primary key (id))
;

create table student (
  id                        bigint not null,
  name                      varchar(255),
  course_id                bigint,
  constraint pk_student primary key (id))
;

alter table student add constraint fk_student_course foreign key (course_id) references course(id) on delete restrict on update restrict;
create index ix_student_course on student(course_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;
drop table if exists student;