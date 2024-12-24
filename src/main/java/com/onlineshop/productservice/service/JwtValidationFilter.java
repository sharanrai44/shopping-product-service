package com.onlineshop.productservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {

    private final String key = "uxo3jOJi3koX6V6GaDft2Ez0iz2K3OWMV6GaDft2Ez0iz2KV6GaDft2Ez0iz2K"; // Shared secret or public key

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

            String roles = claims.get("roles", String.class);
            List<String> rolesList = new ArrayList<>(Arrays.asList(roles.split(",")));

            var authorities = rolesList.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }


}
