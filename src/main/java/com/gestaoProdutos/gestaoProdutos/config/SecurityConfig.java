package com.gestaoProdutos.gestaoProdutos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.GET, "/produtos/**").hasAnyRole("GERENTE", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/produtos/**").hasAnyRole("GERENTE", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/produtos/**").hasAnyRole("GERENTE")
                        .requestMatchers(HttpMethod.DELETE, "/produtos/**").hasAnyRole("GERENTE"))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
