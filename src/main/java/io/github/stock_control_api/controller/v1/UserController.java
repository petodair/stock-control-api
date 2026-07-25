package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.dto.v1.user.UserRequestDTO;
import io.github.stock_control_api.dto.v1.user.UserResponseDTO;
import io.github.stock_control_api.dto.v1.user.UserUpdateResquestDTO;
import io.github.stock_control_api.entity.User;
import io.github.stock_control_api.mapper.v1.UserMapper;
import io.github.stock_control_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> findById(
            @PathVariable UUID uuid
    ){
        User user = userService.findById(uuid);
        return ResponseBuilder.<UserResponseDTO>builder()
                .message("Usuário encontrado com sucesso!")
                .data(userMapper.toDTO(user))
                .ok();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> findAll(){
        List<User> users = userService.findAll();
        return ResponseBuilder.<List<UserResponseDTO>>builder()
                .message("Usuários encontrados com sucesso!")
                .data(users.stream().map(userMapper::toDTO).toList())
                .ok();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> save(
            @RequestBody UserRequestDTO dto
    ){
        User user = this.userService.save(userMapper.toEntity(dto));
        return ResponseBuilder.<UserResponseDTO>builder()
                .data(this.userMapper.toDTO(user))
                .message("Usuário criado com sucesso!")
                .created(user.getId());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> update(
            @RequestBody UserUpdateResquestDTO dto,
            @PathVariable UUID uuid
    ){
        User user = this.userService.update(userMapper.toEntity(dto), uuid);
        return ResponseBuilder.<UserResponseDTO>builder()
                .data(this.userMapper.toDTO(user))
                .message("Usuário atualizado com sucesso!")
                .ok();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID uuid
    ){
        userService.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }
}
