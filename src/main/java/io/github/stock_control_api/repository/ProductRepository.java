package io.github.stock_control_api.repository;

import io.github.stock_control_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    boolean existsByCode(String code);

    @Query("SELECT p FROM Product p JOIN FETCH p.productType pt WHERE pt.name = :typeName")
    List<Product> findByProductTypeName(@Param("typeName") String typeName);
}
