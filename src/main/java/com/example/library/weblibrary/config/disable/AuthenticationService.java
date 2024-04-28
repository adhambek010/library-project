//package com.example.library.weblibrary.user.auth;
//
//import com.example.library.weblibrary.config.disable.JwtService;
//import com.example.library.weblibrary.exception.UserNotFoundException;
//import com.example.library.weblibrary.token.Token;
//import com.example.library.weblibrary.token.TokenRepository;
//import com.example.library.weblibrary.user.entities.User;
//import com.example.library.weblibrary.user.repositories.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final UserRepository userRepository;
//    private final TokenRepository tokenRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService service;
//
//    /**
//     * Registers a new user based on the provided RegisterRequest. Saves the user to the database, generates authentication tokens, and returns an AuthenticationResponse.
//     * @param request RegisterRequest containing user details
//     * @return AuthenticationResponse containing access token, refresh token, and user role
//     */
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .email(request.getEmail())
//                .roles(request.getRole())
//                .build();
//
//        var savedUser = userRepository.save(user);
//        var jwtToken = service.generateToken(user);
//        var refreshToken = service.generateRefreshToken(user);
//        savedUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                //TODO: add role for extract from token
//                .build();
//
//    }
//
//    /**
//     * Saves user's JWT token to the database.
//     * @param user The user whose token needs to be saved
//     * @param jwtToken The JWT token to be saved
//     */
//    public void savedUserToken(User user, String jwtToken) {
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .expired(false)
//                .revoked(false)
//                //TODO: add role also to the token
//                .build();
//        tokenRepository.save(token);
//    }
//
//    /**
//     * Revokes all tokens associated with the given user.
//     * @param user The user whose tokens need to be revoked
//     */
//    public void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty()) {
//            return;
//        }
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
//
//    /**
//     * Refreshes the access token using the refresh token and sends the updated tokens in the response.
//     * @param request The HTTP request containing the refresh token
//     * @param response The HTTP response to send the updated tokens
//     * @throws IOException if an I/O error occurs
//     */
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        //TODO:extract email I don't understand Why email = service.extractUsername(refreshToken);
//        userEmail = service.extractUsername(refreshToken);
//
//        if (userEmail != null) {
//            var user = userRepository.findByEmail(userEmail).orElseThrow();
//            if(service.isValidToken(refreshToken, user)){
//                var accessToken = service.generateToken(user);
//                revokeAllUserTokens(user);
//                savedUserToken(user, accessToken);
//                var authResponse = AuthenticationResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
//
//}
