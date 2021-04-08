-- CREATE database IF NOT EXISTS cdp;
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- uuid generation module

drop table if exists students;
create table students
(
    student_id       integer generated always as identity
        constraint students_pkey
            primary key,
    first_name       varchar not null,
    last_name        varchar not null,
    date_of_birth    date,
    email            varchar,
    phone            varchar,
    primary_skill    varchar,
    created_datetime timestamp,
    updated_datetime timestamp
);

drop table if exists subjects;
create table subjects
(
    subject_id integer generated always as identity
        constraint subjects_pkey
            primary key,
    title      varchar,
    tutor      varchar,
    hours      integer
);

drop table if exists exam_results;
create table exam_results
(
    exam_id    integer generated always as identity
        constraint exam_results_pkey
            primary key,
    student_id integer not null
        constraint fk_student
            references students,
    subject_id integer not null
        constraint fk_subject
            references subjects,
    mark       varchar not null
);
