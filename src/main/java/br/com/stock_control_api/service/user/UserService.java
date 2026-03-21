package br.com.stock_control_api.service.user;

import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findById(Long id);
    List<User> findAll();
    User loadByEmail(String email);
    void delete(Long id);
}
