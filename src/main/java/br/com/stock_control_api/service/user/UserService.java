package br.com.stock_control_api.service.user;

import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.entity.User;

public interface UserService {
    void save(User user);
    User findById(Long id);
    User loadByEmail(String email);
    void delete(Long id);
}
