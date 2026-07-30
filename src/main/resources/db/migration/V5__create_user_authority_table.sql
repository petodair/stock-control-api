CREATE TABLE IF NOT EXISTS tb_user_authority (
        user_id BINARY(16) NOT NULL,
        authority VARCHAR(50) NOT NULL,
        CONSTRAINT fk_user_authority_user FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE
);