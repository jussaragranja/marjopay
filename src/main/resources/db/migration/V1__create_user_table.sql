CREATE TABLE "user" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(240) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    password VARCHAR(255),
    status TINYINT(1) DEFAULT 0,
    email VARCHAR(120) NOT NULL,
    phone VARCHAR(40) NOT NULL,
    date_of_birth TIMESTAMP,
    date_creation TIMESTAMP,
    date_updated TIMESTAMP
);
