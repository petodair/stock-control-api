package io.github.stock_control_api.repository;

import io.github.stock_control_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByCode(String code);
}
