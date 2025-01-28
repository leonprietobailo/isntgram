package com.leonprieto.ig.isntgram_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String header = request.getHeader(AUTHORIZATION_HEADER);
    if (header != null && header.startsWith(BEARER_PREFIX)) {
      final String token = header.substring(7);
      final String username = JwtTokenUtil.validateToken(token);
      if (username == null) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
      final UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request, response);
  }
}
