package com.example.library.weblibrary.user.auth;

import com.example.library.weblibrary.config.security.JwtTokenProvider;
import com.example.library.weblibrary.user.auth.dto.AuthenticationRequest;
import com.example.library.weblibrary.user.auth.dto.AuthenticationResponse;
import com.example.library.weblibrary.user.auth.dto.RegisterRequest;
import com.example.library.weblibrary.user.database.entities.RoleEntity;
import com.example.library.weblibrary.user.database.entities.User;
import com.example.library.weblibrary.user.database.repositories.RoleRepository;
import com.example.library.weblibrary.user.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService1 {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository repository;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<RoleEntity> roleEntity = repository.findByName(request.getRole());
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .username(request.getEmail())
                .roles(Collections.singleton(roleEntity.get()))
                .build();
        userRepository.save(user);

        var accessToken = jwtTokenProvider.generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken("")
                .build();

    }

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var accessToken = jwtTokenProvider.generateToken(userOptional.get().getUsername());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Set-Cookie", "_authorization=Bearer " + accessToken + ";  path=/;")
                .body(new AuthenticationResponse(accessToken, ""));
    }
}
