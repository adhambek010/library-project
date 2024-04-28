CREATE TABLE IF NOT EXISTS roles
(
    identifier VARCHAR(255) NOT NULL,
    name       VARCHAR(20),
    PRIMARY KEY (identifier)
);

INSERT INTO roles (identifier, name)
SELECT '1', 'USER' WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE identifier = '1' AND name = 'USER'
);
INSERT INTO roles (identifier, name)
SELECT '2', 'ADMIN' WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE identifier = '2' AND name = 'ADMIN'
);
INSERT INTO roles (identifier, name)
SELECT '3', 'SUPER_ADMIN' WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE identifier = '3' AND name = 'SUPER_ADMIN'
);