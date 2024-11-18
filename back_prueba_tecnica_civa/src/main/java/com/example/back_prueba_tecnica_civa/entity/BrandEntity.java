package com.example.back_prueba_tecnica_civa.entity;

import com.example.back_prueba_tecnica_civa.commons.constants.DatabaseConstants;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.BRAND_TABLE)
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.BRAND_ID)
    private Integer id;

    @Column(name = DatabaseConstants.BRAND_NAME, nullable = false, length = 50)
    private String brandName;
}
