package br.com.stock_control_api.entity;

import br.com.stock_control_api.enums.BatchLocal;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String batchNumber;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private BatchLocal batchLocal;

    private BigDecimal quantity;

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

}
