package com.example.library.weblibrary.config;

import com.example.library.weblibrary.auditing.ApplicationAuditAware;
import com.example.library.weblibrary.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

    /**
     * Provides the UserDetailsService bean implementation which retrieves user details from the UserRepository.
     *
     * @return An instance of UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found please check credentials")
        );
    }

    /**
     * Provides the AuthenticationProvider bean implementation which configures DaoAuthenticationProvider with custom UserDetailsService and password encoder.
     *
     * @return An instance of AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Provides the AuditorAware bean implementation which determines the current auditor for auditing purposes.
     *
     * @return An instance of AuditorAware
     */
    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new ApplicationAuditAware();
    }

    /**
     * Provides a PasswordEncoder bean implementation for encoding passwords.
     *
     * @return An instance of PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an AuthenticationManager bean implementation using AuthenticationConfiguration.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration instance
     * @return An instance of AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        try {
            return authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
