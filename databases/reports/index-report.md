### INDEX REPORTS

EXPLAIN ANALYSE SELECT code FROM students WHERE first = 'James';
EXPLAIN ANALYSE SELECT code, first FROM students WHERE last LIKE '%fm%';
EXPLAIN ANALYSE SELECT public.students.code, subject_code, mark FROM students JOIN exam_results er on students.code = er.student_code WHERE last LIKE 'j%' AND subject_code = 1100;
