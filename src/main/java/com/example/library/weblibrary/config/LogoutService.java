package com.example.library.weblibrary.config;

//import com.example.library.weblibrary.config.disable.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

//    private final TokenRepository tokenRepository;

    /**
     * Logs out the userEntity by invalidating the JWT token associated with the request.
     * If a valid JWT token is present in the Authorization header, it marks the token as expired and revoked in the token repository.
     * The SecurityContextHolder is cleared to remove the authentication context.
     *
     * @param request The HTTP servlet request
     * @param response The HTTP servlet response
     * @param authentication The authentication object representing the current userEntity's authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        SecurityContextHolder.clearContext();

//        jwtToken = authHeader.substring(7);
//        var storedToken = tokenRepository.findByToken(jwtToken).orElse(null);
//
//        if(storedToken != null) {
//            // Mark the token as expired and revoked
//            storedToken.setExpired(true);
//            storedToken.setRevoked(true);
//            tokenRepository.save(storedToken);
//
//            // Clear the security context
//            SecurityContextHolder.clearContext();
//        }
    }

}
