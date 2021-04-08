insert into cdp.public.students (code, first, last, primary_skill) values
(101, 'John', 'Dow', 'programmer'),
(102, 'Sophia', 'Loren', 'singer'),
(103, 'James', 'Smith', 'analyst'),
(104, 'Maria', 'Rodriguez', 'housewife'),
(105, 'Ralph', 'Lauren', 'designer'),
(106, 'Arthur', 'Conan Doyle', 'writer');

-- update students set first = 'Sofi-2', last = 'Loren-2' where code = 102;

insert into cdp.public.subjects (code, title, tutor, hours) values
(108, 'Mathematics', 'Rene Descartes', 100),
(205, 'Software Engineering', 'Eric Gamma', 50),
(107, 'Compilers', 'Martin Fowler', 75),
(404, 'Type Theory', 'Alan Turing', 55),
(132, 'Clean Code', 'Robert Martin', 20),
(435, 'Algorithms', 'Robert Sedgewick', 60);

insert into exam_results (student_code, subject_code, mark) values
(101, 108, 2), (101, 205, 3), (101, 107, 1),
(102, 404, 5), (102, 132, 2), (102, 435, 3),
(103, 205, 3), (103, 108, 5), (103, 404, 2),
(104, 107, 4), (104, 108, 4), (104, 132, 4),
(105, 205, 1), (105, 108, 1), (105, 107, 3),
(106, 404, 2), (106, 205, 2), (106, 435, 5);
