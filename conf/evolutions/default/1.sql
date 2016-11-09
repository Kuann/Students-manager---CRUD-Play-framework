# --- First database schema

# --- !Ups

create table course (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_course primary key (id))
;

create sequence course_seq start with 1000;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;