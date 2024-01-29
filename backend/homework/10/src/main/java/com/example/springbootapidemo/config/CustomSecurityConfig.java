package com.example.springbootapidemo.config;

import com.example.springbootapidemo.constants.Constants;
import com.example.springbootapidemo.filter.TokenGeneratorFilter;
import com.example.springbootapidemo.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class CustomSecurityConfig {
    @Bean
    @SuppressWarnings("java:S5738")
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.
                addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user").hasAnyRole(Constants.BASIC_ROLE, Constants.ADMIN_ROLE)
                        .requestMatchers(HttpMethod.GET, "/username").hasAnyRole(Constants.BASIC_ROLE, Constants.ADMIN_ROLE)
                        .requestMatchers(HttpMethod.POST, "/user").hasRole(Constants.ADMIN_ROLE)
                        .anyRequest().authenticated()).csrf().disable();
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
