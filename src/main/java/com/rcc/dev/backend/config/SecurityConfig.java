//package com.rcc.dev.backend.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors() // Enable CORS
//                .and()
//                .csrf().disable() // Disable CSRF for development
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll() // Allow all requests for development
//                );
////                .authorizeHttpRequests(authorize -> authorize
////                        .anyRequest().permitAll() // Mengizinkan semua request tanpa autentikasi
////                ).csrf().disable().httpBasic().disable(); // Opsional: menonaktifkan autentikasi HTTP Basic
//
//        return http.build();
//    }
//}
