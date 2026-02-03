CREATE TABLE users (
    id bigint generated always as identity primary key,
    first_name VARCHAR(100),
    last_name VARCHAR(100)
);