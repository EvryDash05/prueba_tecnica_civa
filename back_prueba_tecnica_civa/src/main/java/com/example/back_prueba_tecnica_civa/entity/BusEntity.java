package com.example.back_prueba_tecnica_civa.entity;

import com.example.back_prueba_tecnica_civa.commons.constants.DatabaseConstants;
import com.example.back_prueba_tecnica_civa.commons.enums.BusStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.BUS_TABLE)
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.BUS_ID)
    private Integer id;

    @Column(name = DatabaseConstants.BUS_NUMBER, nullable = false, length = 20)
    private String busNumber;

    @Column(name = DatabaseConstants.BUS_LICENSE_PLATE, nullable = false, unique = true, length = 20)
    private String licensePlate;

    @Column(name = DatabaseConstants.BUS_CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = DatabaseConstants.BUS_FEATURES, columnDefinition = "TEXT")
    private String features;

    @ManyToOne
    @JoinColumn(name = DatabaseConstants.BUS_BRAND_ID, nullable = false)
    private BrandEntity brand;

    @Enumerated(EnumType.STRING)
    @Column(name = DatabaseConstants.BUS_STATUS, nullable = false, length = 10)
    private BusStatus status;

}
