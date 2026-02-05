CREATE TABLE roles (
    id bigint generated always as identity primary key,
    user_id bigint not null,
    name VARCHAR(100) not null,

    constraint fk_roles_user
    foreign key (user_id)
    references users(id)
    on delete cascade
)