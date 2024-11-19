package com.example.back_prueba_tecnica_civa.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String lastName;
    private String email;
    private String message;
    private String token;
    private boolean status;
}
