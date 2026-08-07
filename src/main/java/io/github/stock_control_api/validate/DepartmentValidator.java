package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.Department;
import io.github.stock_control_api.exception.department.DepartmentAlreadyExistsException;
import io.github.stock_control_api.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentValidator implements Validator<Department>{

    private final DepartmentRepository repository;


    @Override
    public void shouldExists(Department entity) {

    }

    @Override
    @Transactional
    public void shouldNotExists(Department entity) {
        boolean exists = repository.existsByNameAndEnterpriseId(
                entity.getName(),
                entity.getEnterprise().getId()
        );

        if (exists) {
            throw new DepartmentAlreadyExistsException(
                    "Já existe um setor com esse nome nessa empresa"
            );
        }
    }

    @Override
    public void checkUpdate(Department newEntity, Department oldEntity) {

    }
}
