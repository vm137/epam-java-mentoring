-- create database cdp;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- uuid module

DROP TABLE IF EXISTS students;
CREATE TABLE IF NOT EXISTS students (
    id UUID DEFAULT uuid_generate_v4(),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    date_of_birth DATE,
    email VARCHAR,
    phones VARCHAR,
    primary_skill VARCHAR,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    PRIMARY KEY (id)
    );

name, surname, dob, phone_numbers, primary_skill, created_datetime, updated_datetime