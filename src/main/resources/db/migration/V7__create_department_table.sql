CREATE TABLE tb_department (
        id BIGINT NOT NULL AUTO_INCREMENT,
        name VARCHAR(255) NOT NULL,
        admin_id BINARY(16),
        enterprise_id BIGINT NOT NULL,

        CONSTRAINT pk_department
        PRIMARY KEY (id),

        CONSTRAINT fk_department_admin
            FOREIGN KEY (admin_id)
                REFERENCES tb_user (id),

        CONSTRAINT fk_department_enterprise
            FOREIGN KEY (enterprise_id)
                REFERENCES tb_enterprise (id)
);