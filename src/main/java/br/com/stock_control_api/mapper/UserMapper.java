package br.com.stock_control_api.mapper;

import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setRoles(dto.roles());
        return user;
    }
}
