-- create database cdp;

drop table if exists students;
create table students
(
    id       integer generated always as identity
        constraint students_pkey
            primary key,
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
        constraint subjects_pkey
            primary key,
    title      varchar,
    tutor      varchar,
    hours      integer
);

drop table if exists exam_results;
create table exam_results
(
    id    integer generated always as identity
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

-- index
create index name on students using hash (last);

-- update timestamp function
create or replace function update_modified_column() returns trigger
    language plpgsql as
$$
BEGIN
    NEW.updated = now();
RETURN NEW;
END;
$$;

create trigger update_customer_modtime
    before update
    on students
    for each row
    execute procedure update_modified_column();

-- data
insert into cdp.public.students (first, last, primary_skill)
values ('John', 'Dow', 'programmer'),
       ('Sofi', 'Laurent', 'singer'),
       ('Arthur', 'Conan Doyle', 'writer');

update students set first='Sofi-2', last = 'Loren-2' where id = 2;

-- validation
