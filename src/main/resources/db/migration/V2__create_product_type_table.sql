CREATE TABLE IF NOT EXISTS tb_product_type(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,

    PRIMARY KEY(id)
);

ALTER TABLE tb_product
ADD COLUMN product_type_id BIGINT,
ADD FOREIGN KEY (product_type_id) REFERENCES tb_product_type (id);