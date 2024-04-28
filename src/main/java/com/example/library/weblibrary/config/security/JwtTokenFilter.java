package com.example.library.weblibrary.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        try {
            // Extract token from the request header
//            String token = jwtTokenProvider.resolveToken(request);
            if (this.isAuthMissing(request) && this.isCookieAuthMissing(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            final String token = this.getCookieHeader(request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                // Extract username from the token
                String username = jwtTokenProvider.getUsername(token);

                // Load userEntity details by username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Check if the userEntity is authenticated
                if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Create authentication token
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set authentication in security context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    populateRequestWithHeaders(response, token);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // In case of any exception, clear the authentication context
            SecurityContextHolder.clearContext();
        }
    }

    private String getAuthHeader(HttpServletRequest request) {
        return !this.isAuthMissing(request) ?
                request.getHeaders("Authorization").nextElement().substring(BEARER.length())
                : this.getCookieAuthHeader(request);
    }

    private String getCookieHeader(HttpServletRequest request) {
        return !this.isCookieAuthMissing(request) ?
                this.getCookieAuthHeader(request)
                : request.getHeaders("Authorization").nextElement().substring(BEARER.length());
    }

    private boolean isAuthMissing(HttpServletRequest request) {
        return !request.getHeaders("Authorization").hasMoreElements();
    }

    private boolean isCookieAuthMissing(HttpServletRequest request) {
        return !request.getHeaders("Cookie").hasMoreElements();
    }

    private String getCookieAuthHeader(HttpServletRequest request) {
        String[] cookies = request.getHeaders("Cookie").nextElement().split(";");
        Map<String, String> cookiesMap = new HashMap<>();
        for (String cookie : cookies) {
            String[] keyValPairs = cookie.split("=");
            cookiesMap.put(keyValPairs[0].toLowerCase().trim(), keyValPairs[1].trim());
        }
        String authorization = cookiesMap.get("_authorization");
        return authorization.substring(BEARER.length());
    }

    private void populateRequestWithHeaders(HttpServletResponse response, String token) {
        String username = jwtTokenProvider.getUsername(token);
        String accessToken = jwtTokenProvider.generateToken(username);
        response.addHeader("Set-Cookie", "_authorization=Bearer " + accessToken + ";  path=/;");
    }
}

