package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.Enterprise;
import io.github.stock_control_api.exception.enterprise.EnterpriseAlreadyExistsException;
import io.github.stock_control_api.repository.EnterpriseRepository;
import io.github.stock_control_api.repository.UserRepository;
import io.github.stock_control_api.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnterpriseValidate {

    private final EnterpriseRepository repository;
    private final UserRepository userRepository;

    public void shoudNotExists(Enterprise enterprise){
        if(repository.existsByName(enterprise.getName())){
            throw new EnterpriseAlreadyExistsException("Já existe uma empresa com esse nome");
        }
    }
}
