CREATE TABLE oauth2_registered_client (
    id TEXT PRIMARY KEY,
    client_id TEXT UNIQUE NOT NULL,
    client_id_issued_at TIMESTAMP NOT NULL,
    client_secret TEXT,
    client_secret_expires_at TIMESTAMP,
    client_name TEXT NOT NULL,
    client_authentication_methods TEXT NOT NULL,
    authorization_grant_types TEXT NOT NULL,
    redirect_uris TEXT,
    post_logout_redirect_uris TEXT,
    scopes TEXT NOT NULL,
    client_settings TEXT NOT NULL,
    token_settings TEXT NOT NULL
);

CREATE TABLE oauth2_authorization (
    id TEXT PRIMARY KEY,
    registered_client_id TEXT NOT NULL,
    principal_name TEXT NOT NULL,
    authorization_grant_type TEXT NOT NULL,
    attributes TEXT,
    state TEXT,
    authorization_code_value TEXT,
    authorization_code_issued_at TIMESTAMP,
    authorization_code_expires_at TIMESTAMP,
    access_token_value TEXT,
    access_token_issued_at TIMESTAMP,
    access_token_expires_at TIMESTAMP,
    access_token_metadata TEXT,
    refresh_token_value TEXT,
    refresh_token_issued_at TIMESTAMP,
    refresh_token_expires_at TIMESTAMP,
    refresh_token_metadata TEXT,
    CONSTRAINT fk_registered_client FOREIGN KEY (registered_client_id) REFERENCES oauth2_registered_client(id)
);

CREATE TABLE oauth2_authorization_consent (
    id TEXT PRIMARY KEY,
    registered_client_id TEXT NOT NULL,
    principal_name TEXT NOT NULL,
    authorities TEXT NOT NULL,
    CONSTRAINT fk_registered_client FOREIGN KEY (registered_client_id) REFERENCES oauth2_registered_client(id)
);

CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users
        FOREIGN KEY (username) REFERENCES users(username),
    CONSTRAINT ix_auth_username UNIQUE (username, authority)
);