package io.github.stock_control_api.repository;

import io.github.stock_control_api.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByNameAndEnterpriseId(String name, Long enterpriseId);
}
