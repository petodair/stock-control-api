package br.com.stock_control_api.repository;

import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:code IS NULL OR LOWER(p.code) LIKE LOWER(CONCAT('%', :code, '%'))) AND " +
            "(:type IS NULL OR p.type = :type)")
    List<Product> findWithFilters(@Param("name") String name,
                                  @Param("code") String code,
                                  @Param("type") ProductType type
    );

    boolean existsByCode(String code);
}
