package br.com.stock_control_api.entity;

import br.com.stock_control_api.enums.BatchLocal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String batchNumber;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private BatchLocal batchLocal;

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

}
