package io.github.stock_control_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.stock_control_api.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${spring.application.name}")
    private String issuer;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try{
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withClaim("roles", user.getRoles())
                    .withExpiresAt(generateExpiration())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            return null;
        }
    }

    public List<String> getRoles(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getClaim("roles").asList(String.class);
        } catch(JWTVerificationException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    public Instant generateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
    }
}
