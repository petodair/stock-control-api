package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.User;
import io.github.stock_control_api.exception.user.UserAlreadyExistsException;
import io.github.stock_control_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

    private void existsByFirstNameAndLastName(User user){
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if(userRepository.existsByFirstNameAndLastName(firstName,lastName)){
            throw new UserAlreadyExistsException("Já existe um usuário com esse nome");
        }
    }
}
