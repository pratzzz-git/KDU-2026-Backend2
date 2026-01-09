package com.corporate.talentportal.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // SAME key as used in JwtService
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    "corporate-secret-key-which-is-at-least-32-bytes-long"
                            .getBytes()
            );

    // Do NOT apply JWT filter on /login
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.substring(7);

                var claims = Jwts.parserBuilder()
                        .setSigningKey(KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                List<String> roles = (List<String>) claims.get("roles");

                var auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        roles.stream()
                                .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                                .toList()
                );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                // Invalid or expired token
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
