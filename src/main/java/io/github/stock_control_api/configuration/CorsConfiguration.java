package io.github.stock_control_api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //Remover isso. Só estou deixando para testar o programa em produção
                .allowedMethods("*")
                .allowedHeaders("*");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
