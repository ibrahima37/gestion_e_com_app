package maboutique.shop.paiementservice.gestionPaiement.config;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commonsecurity.gestionSecurity.config.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // endpoints publics
                        .requestMatchers("/api/paiements/**").authenticated() // endpoints protégés
                        .anyRequest().authenticated()
                )
                .build();
    }
}


