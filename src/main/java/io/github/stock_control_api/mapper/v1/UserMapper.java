package io.github.stock_control_api.mapper.v1;

import io.github.stock_control_api.dto.v1.user.UserRequestDTO;
import io.github.stock_control_api.dto.v1.user.UserResponseDTO;
import io.github.stock_control_api.dto.v1.user.UserUpdateResquestDTO;
import io.github.stock_control_api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserUpdateResquestDTO dto);

    UserResponseDTO toDTO(User user);


}
