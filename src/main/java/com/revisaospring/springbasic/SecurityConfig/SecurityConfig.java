package com.revisaospring.springbasic.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.revisaospring.springbasic.Service.CustomUserDetailService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/usuarioCTR/formCadastrarUsuario",
                    "/usuarioCTR/login",
                    "/usuarioCTR/salvarUsuario",
                    "/usuarioCTR/listarUsuarios", //add a comment in any of these if you want to enable or disable whatever the bank is going to do 
                    "/produtoCTR/**").permitAll() //this one allows all methods to run, using the two asterisks, however its not recommended
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/usuarioCTR/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/usuarioCTR/listarUsuarios", true)
                .permitAll()
            )
            .userDetailsService(userDetailsService)
            .csrf(csrf -> csrf.disable());
            
        return http.build();
    }

}