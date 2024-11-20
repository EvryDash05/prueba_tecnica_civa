package com.example.back_prueba_tecnica_civa.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusResponse {
    private Integer id;
    private String busNumber;
    private String licensePlate;
    private String features;
    private String brand;
    private String status;
}
