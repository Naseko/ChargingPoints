--liquibase formatted sql

--changeset vic.agaf:20221116_initial_db_schema

--create table if not exists app_users (
--    login varchar(255) not null,
--    password varchar(255),
--    primary key (login)
--);

create table if not exists charging_points (
    id bigint not null,
    usn varchar(255) not null,
    name varchar(255),
    primary key (id)
);

create table if not exists connectors (
    id bigint not null,
    number varchar(255) not null,
    charging_point_id bigint,
    primary key (id)
);

--create table if not exists charging_session (
--    id bigint not null,
--    meter double,
--    start_time timestamp,
--    stop_time timestamp,
--    primary key (id)
--);
--
--create table if not exists customers (
--    id bigint not null,
--    name varchar(255),
--    primary key (id)
--);
--
--create table if not exists rfids (
--    number binary(255) not null,
--    primary key (number)
--);
--
--create table if not exists roles (
--    id bigint not null,
--    title varchar(255),
--    primary key (id)
--);
--
--create table if not exists vehicles (
--    reg_plate varchar(255) not null,
--    name varchar(255),
--    primary key (reg_plate)
--);