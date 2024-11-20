package com.example.back_prueba_tecnica_civa.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Integer userId;
    private String username;
    private String email;
    private String message;
    private String token;
    private boolean status;
    private List<String> roles;
}
