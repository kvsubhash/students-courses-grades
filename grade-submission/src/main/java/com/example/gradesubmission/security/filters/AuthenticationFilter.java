package com.example.gradesubmission.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.gradesubmission.models.User;
import com.example.gradesubmission.security.SecurityConstants;
import com.example.gradesubmission.security.manager.CustomAuthenticationManager;
import com.example.gradesubmission.security.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final CustomAuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationFilter(CustomAuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        String jwtToken = jwtUtil.createJWT(authResult.getName());
        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + jwtToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(failed.getMessage());
        response.getWriter().flush();
    }
    
    
    
}
