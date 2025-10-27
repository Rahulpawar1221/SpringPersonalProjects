package com.Learn.DirectAuthFromDb.Configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String path = request.getRequestURI();
        if (path.equals("/api/login") || path.equals("/api/register")) {
            filterChain.doFilter(request, response);
            return;
        }
		// Extract the JWT token from Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        
        // If token is present, proceed with validation
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
        	String token = authorizationHeader.substring(7); // Remove "Bearer " from the token
        	
        	try {
        		// Extract claims from the toaken
        		Claims claims = jwtUtil.extractAllClaims(token);
        		
        		
        		// Check if the token is valid
        		if(jwtUtil.isTokenExpired(token)) {
        			throw new ServletException("Token has expired");
        		}
        		
        		String username = claims.getSubject();
        		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        		UsernamePasswordAuthenticationToken authenticationToken =
        		    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        		
        		// Set the authentication in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        		
        		
        	}catch(Exception e) {
        		// Handle any token-related issues
                logger.error("Token validation failed: ", e);
        	}
        }
        
        // Continue the request chain
        filterChain.doFilter(request, response);	
	}
}
