package br.com.stock_control_api.service.user;

import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.entity.User;
import br.com.stock_control_api.exception.user.UserAlreadyExistsException;
import br.com.stock_control_api.exception.user.UserNotExistsException;
import br.com.stock_control_api.mapper.UserMapper;
import br.com.stock_control_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void save(User user) {
        if(this.userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public User loadByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotExistsException(email));
    }

    @Override
    public void delete(Long id) {
        if(!this.userRepository.existsById(id)) {
            throw new UserNotExistsException("Não existe nenhuma user com o id: " + id);
        }
        this.userRepository.deleteById(id);
    }
}
