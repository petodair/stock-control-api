package io.github.stock_control_api.repository;

import io.github.stock_control_api.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}
