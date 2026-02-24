package br.com.stock_control_api.entity;

import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private MeatType meatType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @OneToMany(mappedBy = "product")
    private List<Batch> batches = new ArrayList<>();
}
