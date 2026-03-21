package br.com.stock_control_api.controller;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.user.UserRequestDTO;
import br.com.stock_control_api.entity.User;
import br.com.stock_control_api.mapper.UserMapper;
import br.com.stock_control_api.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody @Valid UserRequestDTO dto){
        this.userService.save(UserMapper.toEntity(dto));
        return ResponseBuilder.success(HttpStatus.CREATED, "Usuário criado com sucesso!",
                null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<List<User>>> save(){
        return ResponseBuilder.success(HttpStatus.OK, "Usuários retornados com sucesso!",
                this.userService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
