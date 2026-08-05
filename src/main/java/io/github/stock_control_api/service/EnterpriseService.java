package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.Enterprise;
import io.github.stock_control_api.exception.enterprise.EnterpriseNotFoundException;
import io.github.stock_control_api.repository.EnterpriseRepository;
import io.github.stock_control_api.validate.EnterpriseValidator;
import io.github.stock_control_api.validate.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository repository;
    private final EnterpriseValidator validator;
    private final UserValidator userValidator;

    public Enterprise findById(Long id){
        return this.repository.findById(id).orElseThrow(() ->
         new EnterpriseNotFoundException("Empresa não encontrada"));
    }

    public Enterprise save(Enterprise enterprise){
        validator.shouldNotExists(enterprise);
        userValidator.shouldExists(enterprise.getAdmin());
        return repository.save(enterprise);
    }

}
