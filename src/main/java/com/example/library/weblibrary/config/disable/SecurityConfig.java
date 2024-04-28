//package com.example.library.weblibrary.config;
//
//import com.example.library.weblibrary.userEntity.endpoints.Endpoints;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;
//
//    private static final String[] PERMISSIONS = {
//            "/api/v1/auth/**",
//            "/v2/api-docs",
//            "/v3/api-docs",
//            "/v3/api-docs/**",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui/**",
//            "/webjars/**",
//            "/swagger-ui.html"
//
//            //TODO: urllarni ko'rib chiqib kerak emasini ajratib keragini qo'shish kerak
//    };
//
//    /**
//     * Configures the security filter chain for handling authentication and authorization.
//     *
//     * @param http The HttpSecurity object to configure security settings
//     * @return A SecurityFilterChain object representing the configured security filter chain
//     * @throws Exception If an error occurs during configuration
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Disable CSRF protection
//                .csrf(AbstractHttpConfigurer::disable)
//                // Configure authorization rules
//                .authorizeHttpRequests(request -> request
//                                // Permit access to specified URLs
//                                .requestMatchers(PERMISSIONS)
//                                .permitAll()
//                                // Require authentication for any other requests
//                                .anyRequest().authenticated()
//                        // TODO: Add necessary URL patterns to grant access to specific resources
//                )
//                // Set session creation policy to STATELESS to ensure JWT-based authentication
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                // Set custom authentication provider
//                .authenticationProvider(authenticationProvider)
//                // Add JWT authentication filter before the standard UsernamePasswordAuthenticationFilter
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                // Configure logout handling
//                .logout(logout -> logout
//                        // Specify logout URL
//                        .logoutUrl(Endpoints.LOGOUT_USER)
//                        // Add logout handler to clear security context
//                        .addLogoutHandler(logoutHandler)
//                        .addLogoutHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
//                );
//        return http.build();
//    }
//
//}
