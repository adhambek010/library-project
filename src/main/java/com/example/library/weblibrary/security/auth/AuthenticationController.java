package com.example.library.weblibrary.security.auth;

import com.example.library.weblibrary.user.endpoints.Endpoints;
import com.example.library.weblibrary.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final  AuthenticationService service;

    @PostMapping(Endpoints.REGISTER_USER)
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return /*ResponseEntity.ok(service.register(request));*/null;
    }
}
