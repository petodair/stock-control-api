package io.github.stock_control_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String code;

    @Column(precision = 8, scale = 2)
    private BigDecimal price;

    public Product(String name, String code, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }
}
