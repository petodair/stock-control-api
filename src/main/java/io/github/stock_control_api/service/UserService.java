package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.User;
import io.github.stock_control_api.exception.user.UserNotFoundException;
import io.github.stock_control_api.repository.UserRepository;
import io.github.stock_control_api.validate.UserValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserValidate userValidate;
    private final PasswordEncoder encoder;

    public User findById(UUID uuid){
        return this.userRepository.findById(uuid).orElseThrow(() ->
                new UserNotFoundException("Nenhum usuário encontrado com o id: " + uuid));
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User save(User user){
        userValidate.existsByFirstNameAndLastName(user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User newUser, UUID uuid){
        User userFound = findById(uuid);
        this.userValidate.toUpdate(newUser, userFound);
        return this.userRepository.save(userFound);
    }

    public void deleteById(UUID uuid){
        User user = findById(uuid);
        this.userRepository.delete(user);
    }
}
