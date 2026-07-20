CREATE TABLE IF NOT EXISTS tb_batch(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    manufacturing DATE NOT NULL,
    validity DATE NOT NULL,
    quantity DECIMAL(10,2),
    batch_number VARCHAR(255),
    location VARCHAR(30),
    received_at DATE,
    product_id BIGINT NOT NULL,

    CONSTRAINT fk_batch_product
    FOREIGN KEY(product_id)
    REFERENCES tb_product(id)
);