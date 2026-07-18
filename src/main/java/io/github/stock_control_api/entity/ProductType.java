package io.github.stock_control_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
