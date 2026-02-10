CREATE TABLE actions (
    id bigint generated always as identity primary key,
    role_id bigint not null,
    name VARCHAR(100) not null unique,
    description VARCHAR(100),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

INSERT INTO actions (role_id, name, description) VALUES
(1, 'USER_READ', 'Odczyt danych użytkownika'),
(2, 'USER_CREATE', 'Tworzenie użytkownika'),
(2, 'USER_UPDATE', 'Edycja użytkownika'),
(2, 'USER_DELETE', 'Usuwanie użytkownika'),
(1, 'USER_LIST', 'Lista użytkowników'),
(1, 'ROLE_READ', 'Podgląd ról'),
(2, 'ROLE_CREATE', 'Tworzenie ról'),
(2, 'ROLE_UPDATE', 'Edycja ról'),
(2, 'ROLE_DELETE', 'Usuwanie ról'),
(2, 'ROLE_ASSIGN', 'Przypisywanie ról użytkownikom'),
(2, 'ADMIN_PANEL_ACCESS', 'Dostęp do panelu administracyjnego'),
(2, 'SYSTEM_CONFIG_EDIT', 'Edycja konfiguracji systemu'),
(1, 'SYSTEM_LOG_VIEW', 'Podgląd logów systemowych');