package com.chinwendu.studentservice.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @RequiredArgsConstructor
    @EnableMethodSecurity
    @EnableWebSecurity
    public class SecurityFilterConfig {
        private final JwtFilterConfig jwtFilter;
        private final AuthenticationProvider authenticationProvider;
        private final String[] WHITE_LIST = new String[]{
                "/api/v1/students/register",

//                "/swagger-ui/**",
//                "/swagger-ui.html",
               // "/api/v1/students/getListOfCourses"
        };



        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf()
                    .disable()
                    .authorizeHttpRequests()
                    .requestMatchers(WHITE_LIST)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }
    }

