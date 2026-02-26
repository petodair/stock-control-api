package br.com.stock_control_api.repository;

import br.com.stock_control_api.entity.Batch;
import br.com.stock_control_api.enums.BatchLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    @Query("SELECT b FROM Batch b WHERE " +
            "(:batchNumber IS NULL OR LOWER(b.batchNumber) LIKE LOWER(CONCAT('%', :batchNumber, '%'))) AND" +
            "(:batchLocal IS NULL OR b.batchLocal = :batchLocal)")
    List<Batch> findWithFilters(@Param("batchNumber") String batchNumber,
                                @Param("batchLocal") BatchLocal batchLocal);

    boolean existsByBatchNumber(String batchNumber);
}
