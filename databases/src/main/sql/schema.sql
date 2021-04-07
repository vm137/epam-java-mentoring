-- CREATE database IF NOT EXISTS cdp;
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- uuid generation module

DROP TABLE IF EXISTS students;
CREATE TABLE students (
                          student_id UUID DEFAULT uuid_generate_v4(),
                          first_name VARCHAR NOT NULL,
                          last_name VARCHAR NOT NULL,
                          date_of_birth DATE,
                          email VARCHAR,
                          phone VARCHAR,
                          primary_skill VARCHAR,
                          created_datetime TIMESTAMP,
                          updated_datetime TIMESTAMP,
                          PRIMARY KEY (student_id)
);

CREATE INDEX last_name_index ON students USING HASH (last_name);

DROP TABLE IF EXISTS subjects;
CREATE TABLE subjects (
                          subject_id UUID DEFAULT uuid_generate_v4(),
                          title VARCHAR,
                          tutor VARCHAR,
                          hours INTEGER,
                          PRIMARY KEY (subject_id)
);

DROP TABLE IF EXISTS exam_results;
CREATE TABLE exam_results (
                              exam_id UUID DEFAULT uuid_generate_v4(),
                              student_id UUID NOT NULL,
                              subject_id UUID NOT NULL,
                              mark VARCHAR NOT NULL,
                              PRIMARY KEY (exam_id),
                              CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(student_id),
                              CONSTRAINT fk_subject FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);
