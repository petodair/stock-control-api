package br.com.stock_control_api.configuration;

import br.com.stock_control_api.entity.User;
import br.com.stock_control_api.entity.UserAuthenticated;
import br.com.stock_control_api.security.token.TokenService;
import br.com.stock_control_api.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserService userService;

    public SecurityFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                String subject = tokenService.validateToken(token);

                if (subject != null && !subject.isBlank()) {
                    User user = userService.findById(Long.parseLong(subject));
                    UserAuthenticated userAuthenticated = new UserAuthenticated(user);

                    var authentication = new UsernamePasswordAuthenticationToken(
                            userAuthenticated,
                            null,
                            userAuthenticated.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }

        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        if(request.getCookies() == null){
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
