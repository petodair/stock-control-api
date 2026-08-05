CREATE TABLE IF NOT EXISTS tb_enterprise(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    admin_id BINARY(16),
    CONSTRAINT fk_enterprise_admin FOREIGN KEY (admin_id) REFERENCES tb_user(id)
)