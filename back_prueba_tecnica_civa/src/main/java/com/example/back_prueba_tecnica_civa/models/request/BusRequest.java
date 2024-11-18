package com.example.back_prueba_tecnica_civa.models.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusRequest {
    private String busNumber;
    private String licensePlate;
    private String features;
    private String brand;
    private String status;
}
