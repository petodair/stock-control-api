package br.com.stock_control_api.repository;

import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:code IS NULL OR LOWER(p.code) LIKE LOWER(CONCAT('%', :code, '%'))) AND " +
            "(:meatType IS NULL OR p.meatType = :meatType) AND" +
            "(:storageType IS NULL OR p.storageType = :storageType)")
    List<Product> findWithFilters(@Param("name") String name,
                                  @Param("code") String code,
                                  @Param("meatType") MeatType meatType,
                                  @Param("storageType")StorageType storageType
                                  );

    boolean existsByCode(String code);
}
