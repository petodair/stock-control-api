package io.github.stock_control_api.controller.v1;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.dto.v1.LoginRequestDTO;
import io.github.stock_control_api.entity.User;
import io.github.stock_control_api.security.TokenService;
import io.github.stock_control_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(
            @RequestBody LoginRequestDTO request
    ){
        User user = authService.login(request.email(), request.password());
        String token = this.tokenService.generateToken(user);

        ResponseCookie cookie = ResponseCookie.from("token",token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(7200)
                .build();

        return ResponseBuilder.<Void>builder()
                .message("Usuário logado com sucesso")
                .addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
                .ok();
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(){
        ResponseCookie cookie= ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();
        return ResponseBuilder.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("Logout feito com sucesso!")
                .addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
                .ok();
    }

}
