package br.com.stock_control_api.entity;

import br.com.stock_control_api.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @OneToMany(mappedBy = "product")
    private List<Batch> batches;
}
