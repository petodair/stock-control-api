package br.com.stock_control_api.mapper;

import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.dto.user.UserResponseDTO;
import br.com.stock_control_api.entity.User;

public abstract class UserMapper {
    public static User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setRoles(dto.roles());
        return user;
    }

    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
