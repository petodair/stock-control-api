package io.github.stock_control_api.entity;

import io.github.stock_control_api.entity.enums.Location;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate manufacturing;
    private LocalDate validity;
    private BigDecimal quantity;
    private String batchNumber;

    @Enumerated(EnumType.STRING)
    private Location location;
    private LocalDate receivedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
