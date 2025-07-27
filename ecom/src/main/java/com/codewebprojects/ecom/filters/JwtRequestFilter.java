package com.codewebprojects.ecom.filters;


import com.codewebprojects.ecom.services.jwt.UserDetailsServiceImpl;
import com.codewebprojects.ecom.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    public JwtRequestFilter(UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("token");

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract the JWT token after "Bearer "
            // It's good practice to wrap this in a try-catch in case of malformed tokens
            try {
                username = jwtUtil.extractUsername(token); // Extract username from the JWT
            } catch (Exception e) {
                // Log the exception, or handle it as appropriate for your application
                System.err.println("Error extracting username from token: " + e.getMessage());
                // You might want to break or return here if token is invalid
            }
        }

// Check if a username was extracted and if there's no existing authentication in the context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from your UserDetailsService using the extracted username
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            if (jwtUtil.validateToken(token, userDetails)) {
                // If the token is valid, create an authentication token for Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, // The authenticated principal (user)
                        null,        // Credentials (password is not needed as token is validated)
                        userDetails.getAuthorities() // User's authorities/roles
                );

                // Set web authentication details (e.g., remote IP address)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the SecurityContextHolder, marking the user as authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);

    }}