package com.example.gradesubmission.security.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.gradesubmission.security.SecurityConstants;
import com.example.gradesubmission.security.utils.JwtUtil;

public class JwtAuthorizationFilter extends OncePerRequestFilter{

    private final JwtUtil jwtUtil;

    public JwtAuthorizationFilter(JwtUtil  jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith(SecurityConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authHeader.replace(SecurityConstants.BEARER, "");
        String subject = jwtUtil.verifyAndGetSubject(jwtToken);
        Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, Arrays.asList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        filterChain.doFilter(request, response);
    }
    
}
