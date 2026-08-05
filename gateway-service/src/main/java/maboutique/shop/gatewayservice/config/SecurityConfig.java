package maboutique.shop.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // désactiver CSRF
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/auth/**").permitAll() // endpoints publics
                        .pathMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll() // autoriser OPTIONS
                        .anyExchange().authenticated()
                );
        return http.build();
    }
}
