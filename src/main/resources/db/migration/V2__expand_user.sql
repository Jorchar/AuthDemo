ALTER TABLE users
    ADD COLUMN email VARCHAR(255),
    ADD COLUMN created_at TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

UPDATE users
SET created_at = NOW(),
    updated_at = NOW()
WHERE created_at IS NULL;

ALTER TABLE users
    ALTER COLUMN created_at SET NOT NULL,
    ALTER COLUMN updated_at SET NOT NULL;

ALTER TABLE users
    ADD CONSTRAINT uq_users_email UNIQUE (email);

INSERT INTO users (first_name, last_name, email, created_at, updated_at) VALUES
('Anna', 'Kowalska', 'anna.kowalska@example.com', NOW(), NOW()),
('Jan', 'Nowak', 'jan.nowak@example.com', NOW(), NOW()),
('Michał', 'Wiśniewski', 'michal.wisniewski@example.com', NOW(), NOW()),
('Katarzyna', 'Lewandowska', 'katarzyna.lewandowska@example.com', NOW(), NOW()),
('Piotr', 'Zieliński', 'piotr.zielinski@example.com', NOW(), NOW()),
('Magdalena', 'Wójcik', 'magdalena.wojcik@example.com', NOW(), NOW()),
('Tomasz', 'Kamiński', 'tomasz.kaminski@example.com', NOW(), NOW()),
('Agnieszka', 'Kaczmarek', 'agnieszka.kaczmarek@example.com', NOW(), NOW()),
('Paweł', 'Jankowski', 'pawel.jankowski@example.com', NOW(), NOW()),
('Joanna', 'Szymańska', 'joanna.szymanska@example.com', NOW(), NOW());