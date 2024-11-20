package com.example.back_prueba_tecnica_civa.models.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequest {
    private String email;
    private String password;
}
