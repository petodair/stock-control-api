CREATE TABLE IF NOT EXISTS tb_user_roles (
        user_id BINARY(16) NOT NULL,
        role VARCHAR(50) NOT NULL,
        CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE
);