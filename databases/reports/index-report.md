### INDEX REPORTS

## Adding indexes:
create index idx_first on students using hash (first)
completed in 266 ms (13 ms for empty)

create index idx_last on students using btree (last)
completed in 841 ms (11 ms for empty)

create index idx_phone on students using hash (phone)
completed in 245 ms (8 ms for empty)

create index idx_exams on exam_results (student_code, subject_code)
completed in 148 ms (10 ms for empty)

create index idx_subjects on subjects (code)
completed in 13 ms (11 ms for empty)


## Requests without (and with) indexes:
a. EXPLAIN ANALYSE SELECT code FROM students WHERE first = 'James';
Execution Time: 16.622 ms (with index: 0.045 ms)

b. EXPLAIN ANALYSE SELECT code, first FROM students WHERE last LIKE '%fm%';
Execution Time: 28.261 ms (with index: 21.084 ms)

c. EXPLAIN ANALYSE SELECT code, first FROM students WHERE phone=1020489220;
Execution Time: 26.416 ms (with index: 0.085 ms)

d. EXPLAIN ANALYSE SELECT public.students.code, subject_code, mark FROM students JOIN exam_results er on students.code = er.student_code WHERE last LIKE 'j%' AND subject_code = 1100;
Execution Time: 19.135 ms (with index: 16.830 ms)
