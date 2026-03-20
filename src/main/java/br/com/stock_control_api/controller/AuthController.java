package br.com.stock_control_api.controller;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.login.LoginRequestDTO;
import br.com.stock_control_api.dto.user.UserResponseDTO;
import br.com.stock_control_api.entity.User;
import br.com.stock_control_api.entity.UserAuthenticated;
import br.com.stock_control_api.mapper.UserMapper;
import br.com.stock_control_api.security.token.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginDTO,
                                                   HttpServletResponse response) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(),loginDTO.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        UserAuthenticated userAuthenticated = (UserAuthenticated) auth.getPrincipal();

        String jwtToken = this.tokenService.generateToken(userAuthenticated.getUser());

        Cookie cookie = new Cookie("token", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 2);

        response.addCookie(cookie);

        return ResponseBuilder.success(HttpStatus.OK, "Login feito com sucesso!",
               UserMapper.toDTO(userAuthenticated.getUser()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserAuthenticated userAuthenticated = (UserAuthenticated) authentication.getPrincipal();
        return ResponseEntity.ok(UserMapper.toDTO(userAuthenticated.getUser()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }

}
