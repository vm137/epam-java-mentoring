-- create database cdp;

drop table if exists students;
create table students
(
    id       integer generated always as identity
        constraint students_pkey primary key,
    code integer unique,
    first       varchar not null,
    last        varchar not null,
    date_of_birth    date,
    email            varchar,
    phone            varchar,
    primary_skill    varchar,
    created timestamp with time zone default now() not null,
    updated timestamp with time zone default now() not null
);

drop table if exists subjects;
create table subjects
(
    id integer generated always as identity
        constraint subjects_pkey primary key,
    code       integer unique,
    title      varchar,
    tutor      varchar,
    hours      integer
);

drop table if exists exam_results;
create table exam_results
(
    id    integer generated always as identity
        constraint exam_results_pkey primary key,
    student_code integer not null
        constraint fk_student references students(code),
    subject_code integer not null constraint fk_subject
            references subjects(code),
    mark       varchar not null
);

-- index
create index name on students using hash (last);

-- update timestamp function
create or replace function update_modified_column()
    returns trigger
    language plpgsql as
$$
BEGIN
    NEW.updated = now();
RETURN NEW;
END;
$$;

create trigger update_customer_modtime
    before update on students for each row
    execute procedure update_modified_column();

-- validation
alter table students
    add constraint ck_No_Special_Characters
        check (students.first not similar to '%[@#$]%' and
               students.last not similar to '%[@#$]%');

-- ALTER TABLE students DROP CONSTRAINT ck_No_Special_Characters;

-- function average_mark()
create or replace function average_mark()
