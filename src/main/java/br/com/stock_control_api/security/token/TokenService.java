package br.com.stock_control_api.security.token;

import br.com.stock_control_api.entity.User;

public interface TokenService {
    String generateToken(User user);
    String validateToken(String token);
}
