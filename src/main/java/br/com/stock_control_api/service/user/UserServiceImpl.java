package br.com.stock_control_api.service.user;

import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.entity.User;
import br.com.stock_control_api.entity.UserAuthenticated;
import br.com.stock_control_api.exception.user.UserAlreadyExistsException;
import br.com.stock_control_api.exception.user.UserNotExistsException;
import br.com.stock_control_api.mapper.UserMapper;
import br.com.stock_control_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        return new UserAuthenticated(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new UserNotExistsException("Não existe nenhum user com o id: " + id));
    }

    @Override
    public User loadByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotExistsException("Não existe nenhum usuário cadastrado com o email: " + email));
    }

    @Override
    public void delete(Long id) {
        if(!this.userRepository.existsById(id)) {
            throw new UserNotExistsException("Não existe nenhum user com o id: " + id);
        }
        this.userRepository.deleteById(id);
    }
}
