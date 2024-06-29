CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(100,2) NOT NULL,
    user_id BIGINT NOT NULL,
    date_transaction TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);
