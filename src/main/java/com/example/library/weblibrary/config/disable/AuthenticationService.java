//package com.example.library.weblibrary.userEntity.auth;
//
//import com.example.library.weblibrary.config.disable.JwtService;
//import com.example.library.weblibrary.exception.UserNotFoundException;
//import com.example.library.weblibrary.token.Token;
//import com.example.library.weblibrary.token.TokenRepository;
//import com.example.library.weblibrary.userEntity.entities.UserEntity;
//import com.example.library.weblibrary.userEntity.repositories.UserRepository;
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
//     * Registers a new userEntity based on the provided RegisterRequest. Saves the userEntity to the database, generates authentication tokens, and returns an AuthenticationResponse.
//     * @param request RegisterRequest containing userEntity details
//     * @return AuthenticationResponse containing access token, refresh token, and userEntity role
//     */
//    public AuthenticationResponse register(RegisterRequest request) {
//        var userEntity = UserEntity.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .email(request.getEmail())
//                .roles(request.getRole())
//                .build();
//
//        var savedUser = userRepository.save(userEntity);
//        var jwtToken = service.generateToken(userEntity);
//        var refreshToken = service.generateRefreshToken(userEntity);
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
//     * Saves userEntity's JWT token to the database.
//     * @param userEntity The userEntity whose token needs to be saved
//     * @param jwtToken The JWT token to be saved
//     */
//    public void savedUserToken(UserEntity userEntity, String jwtToken) {
//        var token = Token.builder()
//                .userEntity(userEntity)
//                .token(jwtToken)
//                .expired(false)
//                .revoked(false)
//                //TODO: add role also to the token
//                .build();
//        tokenRepository.save(token);
//    }
//
//    /**
//     * Revokes all tokens associated with the given userEntity.
//     * @param userEntity The userEntity whose tokens need to be revoked
//     */
//    public void revokeAllUserTokens(UserEntity userEntity) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(userEntity.getId());
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
//            var userEntity = userRepository.findByEmail(userEmail).orElseThrow();
//            if(service.isValidToken(refreshToken, userEntity)){
//                var accessToken = service.generateToken(userEntity);
//                revokeAllUserTokens(userEntity);
//                savedUserToken(userEntity, accessToken);
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
