create database cdp;

drop table if exists students;
create table students
(
    id integer generated always as identity
        constraint students_pkey primary key,
    code integer unique,
    first varchar not null,
    last varchar not null,
    date_of_birth date,
    email varchar,
    phone varchar,
    primary_skill varchar,
    created timestamp with time zone default now() not null,
    updated timestamp with time zone default now() not null
);

drop table if exists subjects;
create table subjects
(
    id integer generated always as identity
        constraint subjects_pkey primary key,
    code integer unique,
    title varchar,
    tutor varchar,
    hours integer
);

drop table if exists exam_results;
create table exam_results
(
    id integer generated always as identity
        constraint exam_results_pkey primary key,
    student_code integer not null
        constraint fk_student references students(code),
    subject_code integer not null constraint fk_subject
            references subjects(code),
    mark integer not null
);

-- index
create index name on students using hash (last);

-- update timestamp on changes function
create or replace function update_modified_column()
    returns trigger language plpgsql as
$$
BEGIN
    NEW.updated = now();
RETURN NEW;
END;
$$;

create trigger update_customer_modtime
    before update on students for each row
    execute procedure update_modified_column();

-- name validation for special symbols
alter table students
    add constraint ck_No_Special_Characters
        check (students.first not similar to '%[@#$]%' and
               students.last not similar to '%[@#$]%');

-- function: average mark for given student_code
create or replace function average_mark_for_student(integer) returns float
as $$
    select round(avg(mark), 2) from students join exam_results er on students.code = er.student_code and code = $1 group by code;
$$ language sql;
-- example of usage: select * from  average_mark_for_student(103);

-- function: average mark for given subject
create or replace function average_mark_for_subject(integer) returns float
as $$
    select round(avg(mark), 2) from exam_results where subject_code = $1;
$$ language sql;

-- function: find red-zone students (red-zone means at least 2 marks <=3)
create or replace function find_red_zone_students() returns table (student_code integer)
as $$
    select student_code from students join exam_results er on students.code = er.student_code where mark <= 3 group by student_code having count(student_code) >= 2;
$$ language sql;


-- snapshot
