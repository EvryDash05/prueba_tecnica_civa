CREATE TABLE tb_brand
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

CREATE TABLE tb_bus
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
