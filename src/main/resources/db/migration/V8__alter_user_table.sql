ALTER TABLE tb_user
    ADD COLUMN department_id BIGINT,
    ADD CONSTRAINT fk_user_department
        FOREIGN KEY (department_id)
        REFERENCES tb_department (id),

    ADD COLUMN enterprise_id BIGINT,
    ADD CONSTRAINT fk_user_enterprise
        FOREIGN KEY (enterprise_id)
        REFERENCES tb_enterprise (id);