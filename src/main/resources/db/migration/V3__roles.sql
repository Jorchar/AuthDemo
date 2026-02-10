CREATE TABLE roles (
    id bigint generated always as identity primary key,
    name VARCHAR(100) not null unique
);

INSERT INTO roles (name) VALUES
    ('USER'),
    ('ADMIN');

CREATE TABLE user_roles (
    user_id bigint not null,
    role_id bigint not null,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

INSERT INTO user_roles (user_id, role_id) VALUES
    (1, 1),
    (1, 2),
    (2, 1);

INSERT INTO user_roles (user_id, role_id)
SELECT id, 1
FROM users
ON CONFLICT (user_id, role_id) DO NOTHING;