package com.stockQuoteApplication.config;

import com.stockQuoteApplication.exception.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();

    }

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/stockQuote/registerUser").permitAll()
                .requestMatchers("/stockQuote/test").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/stockQuote/getQuote").hasAnyAuthority("user", "admin")
                .requestMatchers("/stockQuote/getAllQuote").hasAuthority("admin")
                .and()
                .formLogin()
                .and().httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        return http.build();

    }
}


