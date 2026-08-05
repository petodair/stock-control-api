package io.github.stock_control_api.repository;

import io.github.stock_control_api.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    boolean existsByName(String name);
}
