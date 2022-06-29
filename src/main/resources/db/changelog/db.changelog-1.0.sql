--liquibase formatted sql

--changeset milavitsky:1
create table employee
(
    employee_id   bigserial   not null
        constraint employee_pk
            primary key,
    first_name    varchar(20) not null,
    last_name     varchar(20) not null,
    department_id bigint,
    job_title     varchar(30),
    gender        varchar(10) not null,
    date_of_birth date
);