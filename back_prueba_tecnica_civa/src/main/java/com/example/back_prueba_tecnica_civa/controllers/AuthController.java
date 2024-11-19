package com.example.back_prueba_tecnica_civa.controllers;

import com.example.back_prueba_tecnica_civa.business.UserDetailsBusiness;
import com.example.back_prueba_tecnica_civa.models.request.AuthCreateUserRequest;
import com.example.back_prueba_tecnica_civa.models.request.AuthLoginRequest;
import com.example.back_prueba_tecnica_civa.models.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDetailsBusiness userDetailsBusiness;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest request) {
        return ResponseEntity.ok(this.userDetailsBusiness.loginUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthCreateUserRequest request) {
        return ResponseEntity.ok(this.userDetailsBusiness.createUser(request));
    }

}
