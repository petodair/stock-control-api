package br.com.stock_control_api.controller;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.login.LoginRequestDTO;
import br.com.stock_control_api.dto.login.LoginResponseDTO;
import br.com.stock_control_api.entity.UserAuthenticated;
import br.com.stock_control_api.security.token.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(),loginDTO.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        UserAuthenticated userAuthenticated = (UserAuthenticated) auth.getPrincipal();

        String jwtToken = this.tokenService.generateToken(userAuthenticated.getUser());

        return ResponseBuilder.success(HttpStatus.OK, "Login feito com sucesso!",
                new LoginResponseDTO(jwtToken));
    }

}
