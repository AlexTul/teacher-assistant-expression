--CREATE TABLE--

create table roles
(
    id   bigserial not null primary key,
    name text      not null unique
);

create table users
(
    id         bigserial not null primary key,
    first_name text      not null,
    last_name  text,
    email      text      not null unique,
    password   text      not null
);

create table user_role
(
    user_id bigserial not null references users (id),
    role_id bigserial not null references roles (id)
);

create table expressions
(
    id bigserial not null primary key,
    expression text not null unique
);

create table roots
(
    id bigserial not null primary key,
    value decimal not null unique
);

create table expression_root
(
    expression_id bigserial not null references expressions (id),
    root_id bigserial not null references roots (id)
);

-- FILL TABLES WITH DEV DATA --

insert into roles (id, name)
values (0, 'ROLE_ADMIN');
insert into roles (id, name)
values (1, 'ROLE_USER');
insert into users (id, first_name, last_name, email, password)
values (0, 'Admin', 'Super', 'admin@gmail.com', '$2a$10$FnMiwqzRc1L/Gq0mHoERfe7Ne1O7AuKUpOtnBdiOD.mX8bURxe336');
insert into user_role (user_id, role_id)
values (0, 0);