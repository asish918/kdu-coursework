CREATE TABLE tenant
(
    id         BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    name       VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    email      VARCHAR(255)
);

CREATE TABLE shift_types
(
    id          BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    uq_name     TEXT,
    description TEXT,
    active      BOOLEAN,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255),
    time_zone   VARCHAR(32),
    tenant_id   BINARY(16),
    CONSTRAINT fk_tenant FOREIGN KEY (tenant_id) REFERENCES tenant (id) ON DELETE CASCADE
);

CREATE TABLE shifts
(
    id            BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    shift_type_id BINARY(16),
    name          VARCHAR(128),
    date_start    DATE,
    date_end      DATE,
    time_start    TIME(6),
    time_end      TIME(6),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    time_zone     VARCHAR(32),
    tenant_id     BINARY(16),
    FOREIGN KEY (shift_type_id) REFERENCES shift_types (id) ON DELETE SET NULL,
    CONSTRAINT fk_shift_tenant FOREIGN KEY (tenant_id) REFERENCES tenant (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id        BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    username  VARCHAR(32),
    loggedin  SMALLINT DEFAULT 0,
    time_zone VARCHAR(32),
    tenant_id BINARY(16),
    CONSTRAINT fk_user_tenant FOREIGN KEY (tenant_id) REFERENCES tenant (id) ON DELETE CASCADE
);

CREATE TABLE shift_user
(
    id        BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID())),
    shift_id  BINARY(16),
    user_id   BINARY(16),
    tenant_id BINARY(16),
    FOREIGN KEY (shift_id) REFERENCES shifts (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_shift_user_tenant FOREIGN KEY (tenant_id) REFERENCES tenant (id) ON DELETE CASCADE
);
