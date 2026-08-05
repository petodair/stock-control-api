package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.Enterprise;
import io.github.stock_control_api.repository.EnterpriseRepository;
import io.github.stock_control_api.validate.EnterpriseValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository repository;
    private final EnterpriseValidate validate;

    public Enterprise save(Enterprise enterprise){
        validate.shoudNotExists(enterprise);
        return repository.save(enterprise);
    }

}
