CREATE TABLE IF NOT EXISTS tb_brand
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(50) NOT NULL
);

INSERT INTO tb_brand (brand_name) VALUES
('Volvo'),
('Scania'),
('Fiat'),
('Mercedes-Benz'),
('MAN'),
('Iveco'),
('Isuzu'),
('Hyundai');

CREATE TABLE IF NOT EXISTS tb_bus
(
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_number VARCHAR(20) NOT NULL,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    features TEXT,
    brand_id INT NOT NULL,
    status ENUM ('Activo', 'Inactivo') NOT NULL DEFAULT 'Activo',
    FOREIGN KEY (brand_id) REFERENCES tb_brand (id) ON DELETE CASCADE
);

INSERT INTO tb_bus (bus_number, license_plate, created_at, features, brand_id, status)
VALUES
    ('1234', 'ABC-123', NOW(), 'Asientos reclinables, WiFi, Aire acondicionado', 1, 'Activo'),
    ('5678', 'DEF-456', NOW(), 'WiFi, Pantallas individuales', 2, 'Inactivo'),
    ('9101', 'GHI-789', NOW(), 'Asientos reclinables, Baño, Cargadores USB', 3, 'Activo'),
    ('1121', 'JKL-012', NOW(), 'Aire acondicionado, Luces LED', 1, 'Inactivo'),
    ('3141', 'MNO-345', NOW(), 'WiFi, Asientos premium', 2, 'Activo');

CREATE TABLE IF NOT EXISTS tb_role (
    role_id VARCHAR(6) NOT NULL,
    role_name VARCHAR(30) UNIQUE,
    PRIMARY KEY (role_id)
);

INSERT INTO tb_role (role_id, role_name) VALUES
('RLE001', 'ADMIN'),
('RLE002', 'USER');

CREATE TABLE IF NOT EXISTS tb_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
    user_id INT NOT NULL,
    role_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES tb_role(role_id)
);
