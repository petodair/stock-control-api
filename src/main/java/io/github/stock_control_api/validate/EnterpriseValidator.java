package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.Enterprise;
import io.github.stock_control_api.exception.enterprise.EnterpriseAlreadyExistsException;
import io.github.stock_control_api.repository.EnterpriseRepository;
import io.github.stock_control_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnterpriseValidator implements Validator<Enterprise>{

    private final EnterpriseRepository repository;
    private final UserRepository userRepository;

    @Override
    public void shouldNotExists(Enterprise enterprise){
        if(repository.existsByName(enterprise.getName())){
            throw new EnterpriseAlreadyExistsException("Já existe uma empresa com esse nome");
        }
    }

    @Override
    public void shouldExists(Enterprise entity) {

    }

    @Override
    public void checkUpdate(Enterprise newEntity, Enterprise oldEntity) {

    }
}
