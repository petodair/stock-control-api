package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.User;
import io.github.stock_control_api.exception.user.UserAlreadyExistsException;
import io.github.stock_control_api.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

    public void existsByFirstNameAndLastName(User user){
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        if(userRepository.existsByFirstNameAndLastName(firstName,lastName)){
            throw new UserAlreadyExistsException("Já existe um usuário com esse nome");
        }
    }

    public void toUpdate(User newUser, User toUpdate){
        if(StringUtils.isNotBlank(newUser.getFirstName())){
            toUpdate.setFirstName(newUser.getFirstName());
        }
        if(StringUtils.isNotBlank(newUser.getLastName())){
            toUpdate.setLastName(newUser.getLastName());
        }
        existsByFirstNameAndLastName(toUpdate);
    }
}
