package br.com.stock_control_api.configuration;

import br.com.stock_control_api.entity.User;
import br.com.stock_control_api.entity.UserAuthenticated;
import br.com.stock_control_api.security.token.TokenService;
import br.com.stock_control_api.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recoveyToken(request);
        if (token != null) {
            String subject = this.tokenService.validateToken(token);
            User user = this.userService.findById(Long.parseLong(subject));
            UserAuthenticated userAuthenticated = new UserAuthenticated(user);
            var authentication = new UsernamePasswordAuthenticationToken(userAuthenticated,
                    null, userAuthenticated.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoveyToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null){
            return null;
        }
        return token.replace("Bearer ", "");
    }
}
