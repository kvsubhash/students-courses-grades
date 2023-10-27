package com.example.gradesubmission.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.gradesubmission.security.filters.AuthenticationFilter;
import com.example.gradesubmission.security.filters.JwtAuthorizationFilter;
import com.example.gradesubmission.security.filters.SecurityExceptionHandlerFilter;
import com.example.gradesubmission.security.manager.CustomAuthenticationManager;
import com.example.gradesubmission.security.utils.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public SecurityConfig(CustomAuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authManager, jwtUtil);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
                .csrf(csrfCustomizer -> csrfCustomizer.disable())
                .headers(headersCustomizer -> headersCustomizer.frameOptions().disable())
                .authorizeRequests(requests -> requests
                        .antMatchers("/h2/**").permitAll()
                        .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new SecurityExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JwtAuthorizationFilter(jwtUtil), AuthenticationFilter.class);

            return http.build();
    }
     
}
